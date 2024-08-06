package com.main.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.library.entity.Patron;

public interface PatronRepo extends JpaRepository<Patron,Long>{

}
