package com.ems.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Employee;
import com.ems.entity.Project;
import com.ems.services.EmployeeServices;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeServices employeeServices;
	
	private PasswordEncoder passwordEncoder;
	
	public EmployeeController(EmployeeServices employeeServices, PasswordEncoder passwordEncoder) {
		this.employeeServices = employeeServices;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		Employee employee=employeeServices.addEmployee(emp);
		return new ResponseEntity<>(employee,HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAllEmployee(){
		List<Employee> emp=employeeServices.findAllEmployee();
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> removeByEmployeeById(@PathVariable long id){
		Employee emp=employeeServices.removeByEmployeeById(id);
		return new ResponseEntity<>(emp,HttpStatus.OK);
	}
	
	@PatchMapping("/addProject/{empId}/{projectId}")
	public ResponseEntity<Employee> PojectAddtoEmployee(@PathVariable long empId, @PathVariable long projectId) {
		Employee emp=employeeServices.PojectAddtoEmployee(empId, projectId);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		
	}
	@PatchMapping("/removeProject/{empId}/{projectId}")
	public  ResponseEntity<Employee> PojectRemoveFromEmployee(@PathVariable long empId, @PathVariable long projectId){
		Employee emp=employeeServices.PojectRemoveFromEmployee(empId, projectId);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@PatchMapping("/addRole/{empId}/{roleID}")
	public ResponseEntity<Employee> ChangeEmployeeRole(@PathVariable Long empId,@PathVariable Long roleId) {
		Employee emp=employeeServices.ChangeEmployeeRole(empId, roleId);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@PatchMapping("/addSallary/{empId}/{roleID}")
	public ResponseEntity<Employee> salaryIncrementEmployee(@PathVariable Long empId,@PathVariable double addSalary) {
		Employee emp=employeeServices.salaryIncrementEmployee(empId, addSalary);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@GetMapping("/sort/{direct}/{type}")
	public ResponseEntity<List<Employee>> getEmployeesByOrderOfType(@PathVariable String direct, @PathVariable String type){
		List<Employee> emp=employeeServices.getEmployeesByOrderOfType(direct, type);
		return new ResponseEntity<List<Employee>>(emp,HttpStatus.OK);
		
	}
	@GetMapping("/sort/{pageNum}/{limit}/{direct}/{type}")
	public ResponseEntity<List<Employee>> getEmployeesByOrderOfTypePageWise(int pageNum, int limit ,String direct, String type){
		List<Employee> emp=employeeServices.getEmployeesByOrderOfTypePageWise(pageNum, limit, direct, type);
		return new ResponseEntity<List<Employee>>(emp,HttpStatus.OK);
	}
	
	@GetMapping("/myAccount")
	public ResponseEntity<Employee> getUserDetails(Authentication auth)
	{
		Employee employee=employeeServices.findEmployeeByEmail(auth.getName());
		System.out.println(employee);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	@GetMapping("/myProject")
	public ResponseEntity<List<Project>> getEmployeeProjects(Authentication auth)
	{
		List<Project> projects=employeeServices.findEmployeeByEmail(auth.getName()).getProjects();
		
		return new ResponseEntity<>(projects,HttpStatus.OK);
	}
	
}
