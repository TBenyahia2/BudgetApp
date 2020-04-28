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
 * Servlet implementation class AccountControllerServlet
 */
@WebServlet("/AccountControllerServlet")
public class AccountControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountDbUtil accountDbUtil;
	
	@Resource(name="jdbc/budget_app_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		//create the account db util and pass in the connection pool / datasource
		try {
			accountDbUtil = new AccountDbUtil(dataSource);
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
				addAccount(request, response);
				break;
			default:
				listAccounts(request, response);
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
		//list accounts in MVC fashion
		try {
			//read command parameter
			String theCommand = request.getParameter("command");
			
			//if command is missing then redirect to a default
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to appropriate method
			switch (theCommand) {
			case "LIST":
				listAccounts(request, response);
				break;
			case "LOAD":
				loadAccount(request, response);
				break;
			case "UPDATE":
				updateAccount(request, response);
				break;
			case "DELETE":
				deleteAccount(request, response);
				break;
			case "VALIDATE":
				validateLogin(request, response);
				break;
			default:
				listAccounts(request, response);
			}
		} 
		catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void validateLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read accountId, pin, and username from form data
		
		//
		
	}

	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read accountId from form data
		String theAccountId = request.getParameter("accountId");
		
		//delete account from db
		accountDbUtil.deleteAccount(theAccountId);
		
		//send back to account list page. TODO send back to Login/Account Create
		listAccounts(request, response);
	}

	private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// read account info from form data
		String id = request.getParameter("accountId");
		String account_name = request.getParameter("account_name");
		String total_amount = request.getParameter("total_amount");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String pin = request.getParameter("pin");
		
		//create account object from data
		Account theAccount = new Account(Integer.parseInt(id), account_name, Double.parseDouble(total_amount), email, username, name, Integer.parseInt(pin));
		
		//perform update on db
		accountDbUtil.updateAccount(theAccount);
		
		//send back to "account list" page
		//TODO send back to the correct logical page after everything built
		listAccounts(request, response);
	}

	private void loadAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get accountId from form data
		String theAccountId = request.getParameter("accountId");
		
		//get account from DB util
		Account theAccount = accountDbUtil.getAccount(theAccountId);
		
		//place account into the request attribute
		request.setAttribute("THE_ACCOUNT", theAccount);
		
		//send to jsp
		//TODO : send to jsp:update-account-form.jsp -WILL NEED TO BE ADJUSTED
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-account-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read account info from form data
		String account_name = request.getParameter("account_name");
		String total_amount = request.getParameter("total_amount");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String pin = request.getParameter("pin");
		
		//create account object
		Account theAccount = new Account(account_name, Double.parseDouble(total_amount), email, username, name, Integer.parseInt(pin));
		
		//add account to database
		accountDbUtil.addAccount(theAccount);
		
		//send back to account page as a redirect to avoid multiple-browser reload additions
		response.sendRedirect(request.getContextPath() + "/AccountControllerServlet?command=LIST");
	}

	private void listAccounts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get accounts from db util
		List<Account> accounts  = accountDbUtil.getAccounts();
		
		//add accounts to the request
		request.setAttribute("ACCOUNT_LIST", accounts);
		
		// send to the jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-administrator-home.jsp");
		dispatcher.forward(request, response);
	}

}
