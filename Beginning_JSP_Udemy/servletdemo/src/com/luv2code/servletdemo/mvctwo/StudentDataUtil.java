package com.luv2code.servletdemo.mvctwo;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	public static List<Student> getStudents(){
		// create an empty list
		List<Student> students = new ArrayList<>();
		
		// add sample data
		students.add(new Student("Marty", "Pubs", "mpubs@email.com"));
		students.add(new Student("Tommy", "Toucans", "totallyTommy@email.com"));
		students.add(new Student("Ding", "Bats", "dbats@email.com"));
		
		//return list
		return students;
	}

}
