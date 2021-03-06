package com.luv2code.web.jdbc;

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
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our student db util , pass in the connection pool / datasource
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	} //end init() override

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//route to the appropriate method
			//checks the command for an input
			if (theCommand == null) {
				theCommand = "LIST";
			}
			//switch accomplishes route
			switch (theCommand) {
			
			case "LIST":
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request, response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request, response);
				break;
			default:
				listStudents(request, response);
			}
			
			// list students , using MVC standard
			listStudents(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	} //end doGet()

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		//delete student from db
		studentDbUtil.deleteStudent(theStudentId);
		
		//send user back to "list students" page
		listStudents(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		//create new student object 
		Student theStudent = new Student(id, firstName, lastName, email);
		
		//perform update on db
		studentDbUtil.updateStudent(theStudent);
		
		// send them back to "list students" page
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//read student id form data
		String theStudentId = request.getParameter("studentId");
		
		//get student from database (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		//place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create new student object
		Student theStudent = new Student(firstName, lastName, email);
		
		// add the student to the database
		studentDbUtil.addStudent(theStudent);
		
		//send back to main page (the list)
		listStudents(request, response);
	}  //end addStudent()

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		// add students to request
		request.setAttribute("STUDENT_LIST", students);
		
		// send to JSP page (view page)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	} //end listStudents()

}
