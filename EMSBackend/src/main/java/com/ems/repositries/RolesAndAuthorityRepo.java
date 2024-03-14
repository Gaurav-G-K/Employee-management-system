package com.ems.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.RolesAndAuthority;

public interface RolesAndAuthorityRepo extends JpaRepository<RolesAndAuthority, Long>{
	Optional<RolesAndAuthority> findByName(String name);
	Optional<RolesAndAuthority> deleteByName(String name);
}
