package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Department;
import com.ems.services.DepartmentServices;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentServices departmentServices;
	
	@PostMapping
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		Department departments=	departmentServices.addDepartment(department);
		return new ResponseEntity<Department>(department,HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Department> removeDeparment(@PathVariable long id) {
		Department department=departmentServices.removeDeparment(id);
		return new ResponseEntity<Department>(department,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Department>> allDepartment(){
		List<Department> departments=departmentServices.allDepartment();
		return new ResponseEntity<List<Department>>(departments,HttpStatus.OK);
	}

}
