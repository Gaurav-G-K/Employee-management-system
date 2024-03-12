package com.ems.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	private String projectName;
	
	
	
	
	@Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
	private Date projectCreationDate;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees=new ArrayList<>();
	
	@JsonIgnore
	private boolean isCompleted;
	
	@ManyToOne(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name="Department_Project",
			joinColumns = {@JoinColumn(name="project_id")},
			inverseJoinColumns= {@JoinColumn(name="department_id")}
			)
	private Department department;
	
	public Project(String projectName, Department department) {
		super();
		this.projectName = projectName;
		this.department = department;
	}
	
	
}
