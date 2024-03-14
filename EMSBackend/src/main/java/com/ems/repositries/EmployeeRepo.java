package com.ems.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	Optional<Employee> findByEmail(String email);
	Optional<Employee> findByEmployeeName(String name);
}
