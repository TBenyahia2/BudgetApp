package com.the_budget_app.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TransactionDbUtil {
	private DataSource dataSource;
	
	public TransactionDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Transaction> getTransactions() throws Exception {
		List<Transaction> transactions = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
					
			//create sql statement
			String sql = "select * from transaction order by account_id";
			myStmt = myConn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process results
			while (myRs.next()) {
				// retrieve data from results set row
				int id = myRs.getInt("id");
				int account_id = myRs.getInt("account_id");
				int member_id = myRs.getInt("member_id");
				double amount = myRs.getDouble("transaction_amount");
				String name = myRs.getString("name");
				String date = myRs.getString("date");				
				
				//create new transaction object
				Transaction tempTransaction = new Transaction(id, account_id, member_id, amount, name, date);
				
				//add it to list of transactions
				transactions.add(tempTransaction);
			}
			return transactions;
		}
		finally {
			//close JDBC objects
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
				myConn.close();  //not a true close, just returns to available connection pool
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTransaction(Transaction theTransaction) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db Connection
			myConn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into transaction (account_id, member_id, date, name, transaction_amount) values (?, ?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			//set the param values
			myStmt.setInt(1, theTransaction.getAccount_id());
			myStmt.setInt(2, theTransaction.getMember_id());
			myStmt.setString(3, theTransaction.getDate());
			myStmt.setString(4, theTransaction.getName());
			myStmt.setDouble(5, theTransaction.getAmount());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
			//clean up JDBC objects
			close(myConn, myStmt, null);
		}		
	}

	public Transaction getTransaction(String theTransactionId) throws Exception {
		Transaction theTransaction = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int transactionId;
		try {
			//convert id to int
			transactionId = Integer.parseInt(theTransactionId);
			
			//get connection to db
			myConn = dataSource.getConnection();
			
			//create sql to get selected transaction
			String sql = "select * from transaction where id=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, transactionId);
			
			//execute the statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if (myRs.next()) {
				int account_id = myRs.getInt("account_id");
				int member_id = myRs.getInt("member_id");
				double amount = myRs.getDouble("transaction_amount");
				String name = myRs.getString("name");
				String date = myRs.getString("date");
				
				theTransaction = new Transaction(transactionId, account_id, member_id, amount, name, date);
			}
			else {
				throw new Exception("Could not find transaction Id: " + transactionId);
			}
			return theTransaction;
		}
		finally {
			//close jdbc objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateTransaction(Transaction theTransaction) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
 		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "update transaction set account_id=?, member_id=?, date=?, name=?, transaction_amount=? where id=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, theTransaction.getAccount_id());
			myStmt.setInt(2, theTransaction.getMember_id());
			myStmt.setString(3, theTransaction.getDate());
			myStmt.setString(4, theTransaction.getName());
			myStmt.setDouble(5, theTransaction.getAmount());
			myStmt.setInt(6, theTransaction.getId());
			
			//execute sql
			myStmt.execute();
 		}
 		finally {
 			//close jdbc objects
 			close(myConn,myStmt, null);
 		}		
	}

	public void deleteTransaction(String theTransactionId) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//convert transaction id to int
			int transactionId = Integer.parseInt(theTransactionId);
			
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql = "delete from transaction where id=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, transactionId);
			
			//execute statement
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}	
}
