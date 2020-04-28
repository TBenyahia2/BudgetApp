package com.the_budget_app.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MemberDbUtil {
	private DataSource dataSource;
	
	public MemberDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Member> getMembers() throws Exception {
		List<Member> members = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create sql statements
			String sql = "select * from member order by id";
			myStmt = myConn.createStatement();
			
			//execute
			myRs = myStmt.executeQuery(sql);
			
			//process results
			while (myRs.next()) {
				//retrieve data from result set 
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String userName = myRs.getString("userName");
				String email = myRs.getString("email");
				int account_id = myRs.getInt("account_id");
				int pin = myRs.getInt("account_pin");
				
				//create member object
				Member tempMember = new Member(id, name, userName, email, account_id, pin);
				
				//add to the member list
				members.add(tempMember);
				
			}
			//close jdbc objects
			
			return members;
		}
		finally {
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

	public void addMember(Member theMember) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create sql for insert
			String sql = "insert into member (name, userName, email, account_id) values (?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			//set the param values
			myStmt.setString(1, theMember.getName());
			myStmt.setString(2, theMember.getUserName());
			myStmt.setString(3, theMember.getEmail());
			myStmt.setInt(4, theMember.getAccount_id());
			
			//execute sql insert
			myStmt.execute();
		}
		finally {
			//clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Member getMember(String theMemberId) throws Exception {
		Member theMember = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int memberId;
		
		try {
			//convert member id to int
			memberId = Integer.parseInt(theMemberId);
			
			//get connection to db
			myConn = dataSource.getConnection();
			
			//creat sql statement to get the selected member
			String sql = "select * from member where id=?";
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, memberId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set
			if(myRs.next()) {
				String firstName = myRs.getString("name");
				String lastName = myRs.getString("userName");
				String email = myRs.getString("email");
				int account_id = myRs.getInt("account_id");
				int pin = myRs.getInt("account_pin");
				
				//use the memberId during construction
				theMember = new Member(memberId, firstName, lastName, email, account_id, pin);
			}
			else {
				throw new Exception("Could not find member id: " + memberId);
			}
			return theMember;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
	}

	public void updateMember(Member theMember) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create SQL update statement
			String sql = "update member set name=?, userName=?, email=? where id=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, theMember.getName());
			myStmt.setString(2, theMember.getUserName());
			myStmt.setString(3, theMember.getEmail());
			myStmt.setInt(4, theMember.getId());
			
			//execute SQL statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteMember(String theMemberId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//Convert memberId  to int
			int memberId = Integer.parseInt(theMemberId);
			
			//get connection to db
			myConn = dataSource.getConnection();
			
			// create sql statement to delete student
			String sql = "delete from member where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, memberId);
			
			//execute sql statement
			myStmt.execute();
		}
		finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
		}		
	}
}
