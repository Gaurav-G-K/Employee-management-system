package com.ems.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.repositries.EmployeeRepo;

@Service
public class EmployeesDetailsServices implements UserDetailsService{
	private EmployeeRepo employeeRepo;
	public EmployeesDetailsServices(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Employee> employee=employeeRepo.findByEmail(email);
		return employee.orElseThrow(()-> new UsernameNotFoundException("No user forund for Email :"+email));
	}

}
