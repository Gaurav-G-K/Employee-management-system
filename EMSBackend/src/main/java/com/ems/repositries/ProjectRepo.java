package com.ems.repositries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
	Optional<Project> findByProjectName(String name);
}
