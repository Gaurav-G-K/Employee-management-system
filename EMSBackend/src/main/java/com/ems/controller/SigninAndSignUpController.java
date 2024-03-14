package com.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.paylode.Login;
import com.ems.paylode.authResponse;
import com.ems.services.EmployeeServices;

@RestController
@RequestMapping("/auth")
public class SigninAndSignUpController {
	
	private EmployeeServices employeeServices;
	public  SigninAndSignUpController(EmployeeServices employeeServices) {
		super();
		this.employeeServices=employeeServices;
	}
	
	@PostMapping("/signIn")	
	public ResponseEntity<authResponse> getLoggedIn(@RequestBody Login login)
	{
		 return  new ResponseEntity<>(employeeServices.loginUser(login),HttpStatus.OK);

	}
	
}
