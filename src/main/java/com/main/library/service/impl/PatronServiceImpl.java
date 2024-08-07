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
public class PatronServiceImpl implements PatronService{

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
		List<BorrowingRecord> records = recordService.findByPatronId(id);
		if (records.size() > 0 && records.get(0).getReturnDate() == null) {
			throw new CustomException("this patron already has a book and not return it yet");
		}
		if(!records.isEmpty()) {
			recordService.deleteAll(records);
		}	
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
