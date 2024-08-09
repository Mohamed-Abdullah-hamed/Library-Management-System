package com.main.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.library.entity.AppRole;

public interface AppRoleRepo extends JpaRepository<AppRole, Long>{

}
