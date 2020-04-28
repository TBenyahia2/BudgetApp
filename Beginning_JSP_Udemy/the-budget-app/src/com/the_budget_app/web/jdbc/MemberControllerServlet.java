package com.the_budget_app.web.jdbc;

import com.sun.net.httpserver.HttpServer;

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
 * Servlet implementation class MemberControllerServlet
 */
@WebServlet("/MemberControllerServlet")
public class MemberControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDbUtil memberDbUtil;
	private AccountDbUtil accountDbUtil;
	
	@Resource(name="jdbc/budget_app_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create member db util and pass into connection pool / datasource
		try {
			memberDbUtil = new MemberDbUtil(dataSource);
			
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			//read the command parameter
			String theCommand = request.getParameter("command");
			switch(theCommand) {
			case "ADD":
				addMember(request, response);
				break;
			default:
				listMembers(request, response);
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
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			//if command missing then default to member list TODO: change default to theMember home
			if (theCommand == null){
				theCommand = "LIST";
			}
			
			//route to the appropriate method
			switch(theCommand) {
				case "LIST":
					listMembers(request, response);
					break;
				case "LOAD":
					loadMember(request, response);
					break;
				case "UPDATE":
					updateMember(request, response);
					break;
				case "DELETE":
					deleteMember(request, response);
				default:
					//TODO : Change default to MemberHome
					listMembers(request, response);
			}
			
		
			//list the members in mvc fashion
			listMembers(request, response);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	
	}




	private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// read member Id from form data
		String theMemberId = request.getParameter("memberId");
		
		//delete member from db
		memberDbUtil.deleteMember(theMemberId);
		
		//send the user back to the list members page
		listMembers(request, response);
	}

	private void updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read member info from form data
		int id = Integer.parseInt(request.getParameter("memberId"));
		String name = request.getParameter("name");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		int account_id = Integer.parseInt("account_id");
		int pin = Integer.parseInt("account_pin");
		
		//create new member object
		Member theMember = new Member(id, name, userName, email, account_id, pin);
		
		//perform update to db
		memberDbUtil.updateMember(theMember);
		
		//send back to the "list-members page TODO: send back to MemberHome
		listMembers(request,response);
	}


	private void loadMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// read memberId from form data
		String theMemberId = request.getParameter("memberId");
		
		//get member from db util
		Member theMember = memberDbUtil.getMember(theMemberId);
		
		//place member in the request attribute
		request.setAttribute("THE_MEMBER", theMember);
		
		//send to the jsp page : update-member-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-member-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read Member form data
		String name = request.getParameter("name");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		int account_id = Integer.parseInt(request.getParameter("account_id"));
		int pin = Integer.parseInt(request.getParameter("account_pin"));
		//create new member object
		Member theMember = new Member(name, userName, email, account_id, pin);
			
		//add member to db
		memberDbUtil.addMember(theMember);
		
		//direct back to theMemberList, send as redirect to avoid multiple browser reloads issue
		//TODO : switch to direct to MemberHome
		response.sendRedirect(request.getContextPath() + "/MemberControllerServlet?command=LIST");
	}

	private void listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get members from db util
		List<Member> members = memberDbUtil.getMembers();
		
		//add members to request
		request.setAttribute("MEMBER_LIST", members);
		
		//send to JSP page for viewing
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-members.jsp");
		dispatcher.forward(request, response);
		
	}


	private void validateLogin(HttpServletRequest request, HttpServerResponse response) throws Exception{

		String userName = request.getParameter("userName");
		int pin = Integer.parseInt(request.getParameter("pinCode"));
		String accountId = request.getParameter("accountId");
		Member member;
		Account account;

		// checks if all credentials are correct

		if(memberDbUtil.validateLogin(accountId, pin, userName) == true){

			// login is correct, check if user is admin
			// loading the account
			account = accountDbUtil.getAccount(accountId);
			member = memberDbUtil.getMember(accountId);

			if(account.getUsername().equals(member.getUserName())){

				// redirect to admin page

			}

			else{

				// redirect to user page
			}

		}


	}


}
