package com.ems.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Project;
import com.ems.services.ProjectServices;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectServices projectServices;
	
	@PostMapping
	public ResponseEntity<Project> addProject(@RequestBody Project project) {
		Project projects=projectServices.addProject(project);
		return new ResponseEntity<>(projects,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> findProjectById(@PathVariable long id) {
		Project project=projectServices.findProjectById(id);
		return new ResponseEntity<>(project,HttpStatus.OK);
		
	}
	@GetMapping("/project/{name}")
	public ResponseEntity<List<Project>> findProjectByName(@PathVariable String name){
		
		List<Project> projects=projectServices.findProjectByName(name);
		return new ResponseEntity<>(projects,HttpStatus.OK);
		
	}
	
	@DeleteMapping
	public ResponseEntity<Project> removeProject(@RequestBody Project project) {
		Project proj=projectServices.removeProject(project);
		return new ResponseEntity<>(proj,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Project> removeProject(@PathVariable long id) {
		Project project=projectServices.findProjectById(id);
		projectServices.removeProject(project);
		return new ResponseEntity<>(project,HttpStatus.OK);
	}
	
	@PatchMapping("/{id}/{date}")
	public ResponseEntity<Project> projectCompleted(long id,LocalDate date) {
		Project project=projectServices.projectCompleted(id, date);
		return new ResponseEntity<>(project,HttpStatus.OK);
	}
	
}
