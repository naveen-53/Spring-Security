package com.example.websecurity.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.websecurity.model.Student;

import jakarta.servlet.http.HttpServletRequest;



@RestController
public class StudentController {
	List<Student> students = new ArrayList<>(Arrays.asList(
									new Student(10,"naveen", 23),
									new Student(11,"Bala",23))
								);
																	
			
	@GetMapping("students")
	public List<Student> getStudents(){
		return students;
	}
	
	/*
	 * @GetMapping("csrf-token") public CsrfToken getCsrfToken(HttpServletRequest
	 * request){ return (CsrfToken) request.getAttribute("_csrf"); }
	 */

	
	@PostMapping("students")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}
	@GetMapping("greet")
	public String  welcome(){
		return "Welcome";
	}


}
