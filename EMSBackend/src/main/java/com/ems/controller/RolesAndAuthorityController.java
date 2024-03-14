package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Project;
import com.ems.entity.RolesAndAuthority;
import com.ems.exceptions.NotFoundException;
import com.ems.services.RolesAndAuthorityServices;

@RestController
@RequestMapping("/roles")
public class RolesAndAuthorityController {
	
	@Autowired
	private RolesAndAuthorityServices rolesAndAuthorityServices;
	
	@PostMapping
	public ResponseEntity<RolesAndAuthority> addRolesAndAuthority(@RequestBody RolesAndAuthority role) {
		RolesAndAuthority newRole=rolesAndAuthorityServices.addRolesAndAuthority(role);
		return new ResponseEntity<RolesAndAuthority>(newRole,HttpStatus.OK);
	}
	@GetMapping("/{name}")
	public ResponseEntity<RolesAndAuthority> getRolesAndAuthority(@PathVariable String name) {
		RolesAndAuthority role=rolesAndAuthorityServices.getRolesAndAuthority(name);
		return new ResponseEntity<RolesAndAuthority>(role,HttpStatus.OK);
		
	}	
	@DeleteMapping("/{name}")
	public ResponseEntity<RolesAndAuthority> deletRolesAndAuthorityByName(@PathVariable String name) {
		RolesAndAuthority role=rolesAndAuthorityServices.deletRolesAndAuthorityByName(name);		
		return new ResponseEntity<RolesAndAuthority>(role,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<RolesAndAuthority> deletRolesAndAuthorityById(Long id) {
		RolesAndAuthority role=rolesAndAuthorityServices.deletRolesAndAuthorityById(id);
		return new ResponseEntity<RolesAndAuthority>(role,HttpStatus.OK);
	}
	
	
}
