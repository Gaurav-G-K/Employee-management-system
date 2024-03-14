package com.ems.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ems.entity.Department;
import com.ems.entity.Project;
import com.ems.exceptions.NotFoundException;
import com.ems.repositries.DepartmentRepo;
import com.ems.repositries.ProjectRepo;

@Service
public class ProjectServices {

	private ProjectRepo projectRepo;

	private DepartmentRepo departmentRepo;
	public ProjectServices(ProjectRepo projectRepo,DepartmentRepo departmentRepo) {
		super();
		this.projectRepo = projectRepo;
		this.departmentRepo=departmentRepo;
	}
	public Project addProject(Project project) {
		
		if(projectRepo.findByProjectName(project.getProjectName()).isPresent())
			throw new NotFoundException("Project Name already exits");
		
		Department depart=departmentRepo.findByDepartmentName(project.getDepartment().getDepartmentName()).get();
		if(depart!=null)
			project.setDepartment(depart);
		return projectRepo.save(project);
	}
	public Project findProjectById(long id) {
		Project project=projectRepo.findById(id).get();
		if(project!=null)
			throw new NotFoundException("Project id not exits");
		return project;
	}
	public List<Project> findProjectByName(String name) {
		List<Project> project=(List<Project>) projectRepo.findByProjectName(name).get();
		if(project!=null)
			throw new NotFoundException("Project Name not exits");
		return project;
	}
	
	public Project removeProject(Project project) {
		Project projects=projectRepo.findById(project.getProjectId()).get();
		if(projects==null)
			throw new NotFoundException("Project id not exits");
		projectRepo.delete(project);
		return projects;
	}
	
	public Project projectCompleted(long id,LocalDate date) {
		Project project=projectRepo.findById(id).get();
		if(project==null)
			throw new NotFoundException("Project id not exits");
		project.setCompleted(true);
		if(!date.isAfter(project.getStartDate()))
			throw new NotFoundException("Project completion date should not past of start date");
		project.setEndDate(date);
		
		return project;
	}
}
