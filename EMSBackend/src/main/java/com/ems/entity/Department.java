package com.ems.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentId;
	@Column(unique = true,nullable = false)
	private String departmentName;
	@JsonIgnore
	@OneToMany(mappedBy = "department")
	private List<Project> project;
	@JsonIgnore
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
	public Department(String departmentName, List<Project> project) {
		super();
		this.departmentName = departmentName;
		this.project = project;
	}
	
	
}

