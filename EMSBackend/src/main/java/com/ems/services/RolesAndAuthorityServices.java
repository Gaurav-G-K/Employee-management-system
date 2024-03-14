package com.ems.services;

import org.springframework.stereotype.Service;

import com.ems.entity.RolesAndAuthority;
import com.ems.exceptions.NotFoundException;
import com.ems.repositries.RolesAndAuthorityRepo;
@Service
public class RolesAndAuthorityServices {

	private RolesAndAuthorityRepo roleAndAuthorityRepo;

	public RolesAndAuthorityServices(RolesAndAuthorityRepo roleAndAuthorityRepo) {
		super();
		this.roleAndAuthorityRepo=roleAndAuthorityRepo;
	}
	
	public RolesAndAuthority addRolesAndAuthority(RolesAndAuthority role) {
		
		RolesAndAuthority roles=roleAndAuthorityRepo.findByName(role.getName()).get();
		if(roles!=null)
			throw new NotFoundException("RolesAndAuthority Name already exits");
		return roleAndAuthorityRepo.save(role);
	}
	
	public RolesAndAuthority getRolesAndAuthority(String name) {
		RolesAndAuthority roles=roleAndAuthorityRepo.findByName(name).get();
		if(roles==null)
			throw new NotFoundException("RolesAndAuthority Name not exits");
		return roles;
	}
	
	public RolesAndAuthority deletRolesAndAuthorityByName(String name) {
		RolesAndAuthority role=roleAndAuthorityRepo.findByName(name).get();
		if(role==null)
			throw new NotFoundException("RolesAndAuthority Name not exits");
		roleAndAuthorityRepo.deleteById(role.getId());
		return role;
		
	}
	public RolesAndAuthority deletRolesAndAuthorityById(Long id) {
		RolesAndAuthority role=roleAndAuthorityRepo.findById(id).get();
		if(role==null)
			throw new NotFoundException("RolesAndAuthority Id not exits");
		roleAndAuthorityRepo.deleteById(id);
		return role;
	}
}
