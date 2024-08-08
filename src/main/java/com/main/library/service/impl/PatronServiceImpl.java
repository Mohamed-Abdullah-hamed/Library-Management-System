package com.main.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.Patron;
import com.main.library.exception.CustomException;
import com.main.library.repo.PatronRepo;
import com.main.library.service.BorrowingRecordService;
import com.main.library.service.PatronService;

@Service
public class PatronServiceImpl implements PatronService {

	@Autowired
	private PatronRepo patronRepo;
	@Autowired
	private BorrowingRecordService recordService;

	@Override
	public Optional<Patron> findById(Long id) {
		return patronRepo.findById(id);
	}

	@Override
	public List<Patron> findAll() {
		return patronRepo.findAll();
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Optional<Patron> patron = findById(id);
		if (patron.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		List<BorrowingRecord> records = recordService.findByPatronId(id);
		if (records.size() > 0 && records.get(0).getReturnDate() == null) {
			throw new CustomException("this patron already has a book and not return it yet");
		}
		if (!records.isEmpty()) {
			recordService.deleteAll(records);
		}
		patronRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Patron updatePatron(Patron patron, Long id) {
		Optional<Patron> b = findById(id);
		if (b.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		patron.setId(id);
		try {
			Patron updatedPatron = save(patron);
			return updatedPatron;
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate")) {
				throw new CustomException("the Identity Number or Phone Number Is Present Before ");
			}
			throw new RuntimeException();
		}
	}

	@Override
	public Patron save(Patron patron) {
		try {
			Patron savedBook = patronRepo.save(patron);
			return savedBook;
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate")) {
				throw new CustomException("the Identity Number or Phone Number Is Present Before ");
			}
			throw new RuntimeException();
		}
	}

}
