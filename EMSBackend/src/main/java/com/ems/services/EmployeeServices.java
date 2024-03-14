package com.ems.services;

//import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.config.JwtTokenGeneratorFilter;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.entity.Project;
import com.ems.entity.RolesAndAuthority;
import com.ems.exceptions.NotFoundException;
import com.ems.paylode.Login;
import com.ems.paylode.authResponse;
import com.ems.repositries.DepartmentRepo;
import com.ems.repositries.EmployeeRepo;
import com.ems.repositries.ProjectRepo;
import com.ems.repositries.RolesAndAuthorityRepo;

@Service
public class EmployeeServices {
	private ProjectRepo projectRepo;
	private EmployeeRepo employeeRepo;
	private DepartmentRepo departmentRepo;
	private RolesAndAuthorityRepo rolesAndAuthorityRepo;
	private JwtTokenGeneratorFilter jwtTokenGeneratorFilter;
	private PasswordEncoder passwordEncoder;
	private EmployeesDetailsServices employeesDetailsServices;
	
	public EmployeeServices(EmployeeRepo employeeRepo,RolesAndAuthorityRepo rolesAndAuthorityRepo,ProjectRepo projectRepo,DepartmentRepo departmentRepo, JwtTokenGeneratorFilter jwtTokenGeneratorFilter, PasswordEncoder passwordEncoder,EmployeesDetailsServices employeesDetailsServices) {
		this.employeeRepo=employeeRepo;
		this.rolesAndAuthorityRepo=rolesAndAuthorityRepo;
		this.projectRepo=projectRepo;
		this.departmentRepo=departmentRepo;
		this.jwtTokenGeneratorFilter=jwtTokenGeneratorFilter;
		this.passwordEncoder=passwordEncoder;
		this.employeesDetailsServices=employeesDetailsServices;
	}
	public Employee addEmployee(Employee employee) {
		
		if(employeeRepo.findByEmail(employee.getEmail()).isPresent())
			throw new NotFoundException("employee email id already exits");
		Department depart=departmentRepo.findByDepartmentName(employee.getDepartment().getDepartmentName()).get();
		employee.setDepartment(depart);
		System.out.println(depart.getDepartmentName());
		RolesAndAuthority role=rolesAndAuthorityRepo.findByName(employee.getRole().getName()).get();
		System.out.println(role.getName());
		System.out.println(role.getId());
		employee.setRole(role);
		return employeeRepo.save(employee);
	}
	
	public List<Employee> findAllEmployee() {	
		List<Employee> emp=employeeRepo.findAll();
		if(emp==null)
			throw new NotFoundException("No Employee exits");
		return emp;
	}
	
	public Employee removeByEmployeeById(Long id) {
		Employee emp=employeeRepo.findById(id).get();
		if(emp==null)
			throw new NotFoundException("Employee id not exits");
		if(emp.getRole().getName()=="ROLE_ADMIN")
			throw new NotFoundException("You can't remove Your superior");
		emp.setRole(null);
		employeeRepo.delete(emp);
		return emp;
	}
	public Employee PojectAddtoEmployee(Long empId,Long pojectId) {
		Employee emp=employeeRepo.findById(empId).get();
		if(emp==null)
			throw new NotFoundException("Employee id not exits");
		Project project =projectRepo.findById(pojectId).get();
		if(project==null)
			throw new NotFoundException("Project id not exits");
		emp.getProjects().add(project);

		return	employeeRepo.save(emp);
	}
	public Employee PojectRemoveFromEmployee(Long empId,Long pojectId){
		Employee emp=employeeRepo.findById(empId).get();
		if(emp==null)
			throw new NotFoundException("Employee id not exits");
		Project project =projectRepo.findById(pojectId).get();
		if(project==null)
			throw new NotFoundException("Project id not exits");
		emp.getProjects().remove(project);
		return	employeeRepo.save(emp);
	}
	public Employee findEmployeeByEmail(String email) {
		Employee emp=employeeRepo.findByEmail(email).get();
		if(emp==null)
			throw new NotFoundException("Employee Email not exits");
		return emp;
	}
	public Employee ChangeEmployeeRole(Long empId,Long roleId)
	{
		Employee emp=employeeRepo.findById(empId).get();
		if(emp==null)
			throw new NotFoundException("Employee id not exits");
		RolesAndAuthority role=rolesAndAuthorityRepo.findById(roleId).get();
		if(role==null)
			throw new NotFoundException("Role id not exits");
		emp.setRole(role);
		return employeeRepo.save(emp);
	}
	public Employee salaryIncrementEmployee(Long empId,double addSalary)
	{
		Employee emp=employeeRepo.findById(empId).get();
		if(emp==null)
			throw new NotFoundException("Employee id not exits");
		emp.setSalary(emp.getSalary()+addSalary);
		return employeeRepo.save(emp);
	}
	public List<Employee> getEmployeesByOrderOfType(String direct, String type){
		Sort sortEmp = direct.equalsIgnoreCase("ASC")?Sort.by(Sort.Direction.ASC, type):Sort.by(Sort.Direction.DESC);
		List<Employee> emp=employeeRepo.findAll(sortEmp);
		if(emp==null)
			throw new NotFoundException("Employees not exits");
		return emp;
	}
	public List<Employee> getEmployeesByOrderOfTypePageWise(int pageNum, int limit ,String direct, String type){
		Pageable pageable=PageRequest.of(pageNum-1, limit, direct.equalsIgnoreCase("DESC")?Direction.DESC:Direction.ASC,type);
		Page<Employee> page=employeeRepo.findAll(pageable);
		List<Employee> emp=page.getContent();
		if(emp==null)
			throw new NotFoundException("Employees not exits");
		return emp;
	}
	
	public authResponse loginUser(Login login) {
        Authentication authentication = authenticate(login.getUsername(), login.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGeneratorFilter.genrateToken(authentication);
//        String token = jwtTokenGeneratorFilter.genrateToken();
        authResponse auth=new authResponse(token, "Sign In Success");
        return auth; 
    }
	private Authentication authenticate(String username, String password) {
        UserDetails userDetails = employeesDetailsServices.loadUserByUsername(username);
        if (userDetails == null)
            throw new BadCredentialsException("Invalid Username");
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Invalid Password");
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
