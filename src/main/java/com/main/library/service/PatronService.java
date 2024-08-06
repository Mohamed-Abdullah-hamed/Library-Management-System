package com.main.library.service;

import java.util.List;
import java.util.Optional;

import com.main.library.entity.Patron;

public interface PatronService {

	Optional<Patron> findById(Long id);
	List<Patron> findAll();
	void deleteById(Long id);
	Patron updatePatron(Patron patron);
	Patron save(Patron patron);
}
