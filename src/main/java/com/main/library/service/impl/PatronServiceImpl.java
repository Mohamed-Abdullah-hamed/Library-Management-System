package com.main.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.library.entity.Patron;
import com.main.library.repo.PatronRepo;
import com.main.library.service.PatronService;

@Service
public class PatronServiceImpl implements PatronService{

	@Autowired
	private PatronRepo patronRepo;
	@Override
	public Optional<Patron> findById(Long id) {
		return patronRepo.findById(id);
	}

	@Override
	public List<Patron> findAll() {
		return patronRepo.findAll();
	}

	@Override
	public void deleteById(Long id) {
		patronRepo.deleteById(id);
	}

	@Override
	public Patron updatePatron(Patron patron) {
		return patronRepo.save(patron);
	}

	@Override
	public Patron save(Patron patron) {
		return patronRepo.save(patron);
	}

}
