package com.ems.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department,Long>{
	Optional<Department> findByDepartmentName(String name);
}
