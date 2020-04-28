package com.the_budget_app.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TransactionControllerServlet
 */
@WebServlet("/TransactionControllerServlet")
public class TransactionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TransactionDbUtil transactionDbUtil;
	
	@Resource(name="jdbc/budget_app_db")
	private DataSource dataSource;
		
	@Override
	public void init() throws ServletException {
		super.init();
		//create the transaction DB util and pass it into the connection pool / datasource
		try {
			transactionDbUtil = new TransactionDbUtil(dataSource);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//route to appropriate method
			switch (theCommand) {
			case "ADD":
				addTransaction(request, response);
				break;
			default:
				listTransactions(request, response);
			}
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read command parameter
			String theCommand = request.getParameter("command");
			
			//if command is missing then default to the list
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to appropriate method
			switch (theCommand) {
			case "LIST":
				listTransactions(request, response);
				break;
			case "LOAD":
				loadTransaction(request,response);
				break;
			case "UPDATE":
				updateTransaction(request, response);
				break;
			case "DELETE":
				deleteTransaction(request, response);
				break;
			default:
				listTransactions(request, response);
			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteTransaction(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read transaction id from form data
		String theTransactionId = request.getParameter("transactionId");
		
		//delete transaction from db
		transactionDbUtil.deleteTransaction(theTransactionId);
		
		//send back to list transactions db
		listTransactions(request, response);
	}

	private void updateTransaction(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read transaction info from form data
		int id = Integer.parseInt(request.getParameter("transactionId"));
		int account_id = Integer.parseInt(request.getParameter("account_id"));
		int member_id = Integer.parseInt(request.getParameter("member_id"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		
		//create new object based on data
		Transaction theTransaction = new Transaction(id, account_id, member_id, amount, name, date);
		
		//perform db update
		transactionDbUtil.updateTransaction(theTransaction);
		
		//send back to list transactions jsp
		listTransactions(request, response);
		
	}

	private void loadTransaction(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read transaction id from form data
		String theTransactionId = request.getParameter("transactionId");
		
		//get transaction from db
		Transaction theTransaction = transactionDbUtil.getTransaction(theTransactionId);
		
		//place transaction in request
		request.setAttribute("THE_TRANSACTION", theTransaction);
		
		//send to jsp page: update-transaction-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-transaction-form.jsp");
		dispatcher.forward(request, response);
		
		
	}

	private void addTransaction(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read transaction info from form data
		String account_id = request.getParameter("account_id");
		String member_id = request.getParameter("member_id");
		String amount = request.getParameter("amount");
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		
		//create new transaction object
		Transaction theTransaction = new Transaction(Integer.parseInt(account_id), Integer.parseInt(member_id), 
			Double.parseDouble(amount), name, date);
		
		//add to DB
		transactionDbUtil.addTransaction(theTransaction);
		
		//send back to the list as redirect to avoid multiple browser reload
		response.sendRedirect(request.getContextPath() + "/TransactionControllerServlet?command=LIST");
	}

	private void listTransactions(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//get transactions from db util
		List<Transaction> transactions = transactionDbUtil.getTransactions();
		
		//add transactions to the request
		request.setAttribute("TRANSACTION_LIST", transactions);
		
		//send to the jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-transactions.jsp");
		dispatcher.forward(request, response);
	}
}
