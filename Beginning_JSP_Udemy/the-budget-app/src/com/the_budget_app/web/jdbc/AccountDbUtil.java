package com.the_budget_app.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AccountDbUtil {
	private DataSource dataSource;

	public AccountDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Account> getAccounts() throws Exception {
		List<Account> accounts = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "select * from account order by account_name";
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String account_name = myRs.getString("account_name");
				double total_amount = myRs.getDouble("total_amount");
				String email = myRs.getString("email");
				String username = myRs.getString("username");
				String name = myRs.getString("name");
				int pin = myRs.getInt("pin");
				
				//create new account object
				Account tempAccount = new Account(id, account_name, total_amount, email, username, name, pin);
								
				//add it to list of accounts
				accounts.add(tempAccount);				
			}
			return accounts;
		}
		finally {
			// close jdbc objects
			close(myConn, myStmt, myRs);
		}
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close(); //not a true close but frees connection for use
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addAccount(Account theAccount) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into account "
					+ "(account_name, total_amount, email, username, name, pin) "
					+ "values (?, ?, ?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			//set the param values for the account
			myStmt.setString(1, theAccount.getAccount_name());
			myStmt.setString(2, String.valueOf(theAccount.getTotal_amount()));
			myStmt.setString(3, theAccount.getEmail());
			myStmt.setString(4, theAccount.getUsername());
			myStmt.setString(5, theAccount.getName());
			myStmt.setString(6, String.valueOf(theAccount.getPin()));
			
			//execute sql insertion
			myStmt.execute();		
		}
		finally {
			//clean up jdbc objects
			close(myConn, myStmt, null);
		}
	}

	public Account getAccount(String accountId, String accountPin) throws Exception {
		Account theAccount = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int theAccountId = Integer.parseInt(accountId);;
		int pin = Integer.parseInt(accountPin);
		
		
		try {			
			//get connection to DB
			myConn = dataSource.getConnection();
			
			//admin check
			//create sql statement to the get desired account
			String sql = "select * from account where id=? and pin=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params for query
			myStmt.setInt(1, theAccountId);
			myStmt.setInt(2, pin);
			
			//execute the statement
			myRs = myStmt.executeQuery();
			
			//retrieve the data from the result set
			if (myRs.next()) {
				String account_name = myRs.getString("account_name");
				double total_amount = Double.parseDouble(myRs.getString("total_amount"));
				String email = myRs.getString("email");
				String username = myRs.getString("username");
				String name = myRs.getString("name");
				
				//use the accountId during construction
				theAccount = new Account(theAccountId, account_name, total_amount, email, username, name, pin);
			}
			else {
				throw new Exception("Could not find the Account for AccountId: " + accountId + " and the given pin.");
			}		
		return theAccount;
		}
		finally {
			//clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateAccount(Account theAccount) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "update account set account_name=?, total_amount=?, email=?, username=?, name=?, pin=? where id=?";
			
			//prepare the statement
			myStmt = myConn.prepareStatement(sql);
			
			//set the parameters for the statement
			myStmt.setString(1, theAccount.getAccount_name());
			myStmt.setDouble(2, theAccount.getTotal_amount());
			myStmt.setString(3, theAccount.getEmail());
			myStmt.setString(4, theAccount.getUsername());
			myStmt.setString(5, theAccount.getName());
			myStmt.setInt(6, theAccount.getPin());
			myStmt.setInt(7, theAccount.getId());
			
			//execute the sql statement
			myStmt.execute();
		}
		finally {
			//close JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteAccount(String theAccountId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//convert account Id to int
			int accountId = Integer.parseInt(theAccountId);
			
			//get connection to db
			myConn = dataSource.getConnection();
			
			//create sql statement to delete Account TODO: and delete associated members
			String sql = "delete from account where id=?";
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
						
			//set params
			myStmt.setInt(1, accountId);
			
			//execute sql statement
			myStmt.execute();			
		}
		finally {
			//clean up jdbc objects
			close(myConn, myStmt, null);
		}		
	}
}
