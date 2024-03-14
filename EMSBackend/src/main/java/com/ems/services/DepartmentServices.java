package com.ems.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ems.entity.Department;
import com.ems.repositries.DepartmentRepo;

@Service
public class DepartmentServices {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	public Department addDepartment(Department department) {
		
	return departmentRepo.save(department);
	}
	
	public Department removeDeparment(long id) {
		Department depart=departmentRepo.findById(id).get();
		departmentRepo.delete(depart);
		return depart;
	}
	public List<Department> allDepartment(){
	return departmentRepo.findAll();
	}
}