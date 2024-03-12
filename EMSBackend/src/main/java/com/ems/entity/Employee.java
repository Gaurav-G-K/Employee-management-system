package com.ems.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class Employee implements UserDetails, CredentialsContainer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    
    private String employeeName;
    
    private String email;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="users_role",
			joinColumns = @JoinColumn(name="Employee_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private RolesAndAuthority role;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name="Employee_Department",
			joinColumns = {@JoinColumn(name="employee_id")},
			inverseJoinColumns= {@JoinColumn(name="department_id")}                                
			)
	private Department department;
    
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "emplyeesProjects",
    joinColumns = @JoinColumn(referencedColumnName = "employeeId", name = "empId"),
    inverseJoinColumns = @JoinColumn(referencedColumnName = "projectId", name = "projectId"))
    private List<Project> projects=new ArrayList<>();
    
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(name = "hire_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date hireDate;
    
    private double salary;
    
    @JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	public Employee(String employeeName, String email, RolesAndAuthority role, double salary, String password) {
		super();
		this.employeeName = employeeName;
		this.email = email;
		this.role = role;
		this.salary = salary;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> set=new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.role.getName()));
		return set;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void eraseCredentials() {
		this.password=null;
		
	}
        
}
