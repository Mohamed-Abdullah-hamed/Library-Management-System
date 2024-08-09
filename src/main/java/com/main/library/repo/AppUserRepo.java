package com.main.library.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.library.entity.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long>{

	@EntityGraph(attributePaths = {"roles"})
	AppUser findByName(String username);
}
