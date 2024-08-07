package com.main.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.library.entity.BorrowingRecord;
import com.main.library.repo.BorrowingRecordRepo;
import com.main.library.service.BorrowingRecordService;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService{

	@Autowired
	private BorrowingRecordRepo recordRepo;
	@Override
	public Optional<BorrowingRecord> findById(Long id) {
		
		return recordRepo.findById(id);
	}

	@Override
	public List<BorrowingRecord> findAll() {
		return recordRepo.findAll();
	}

	@Override
	public void deleteById(Long id) {
		recordRepo.deleteById(id);
	}

	@Override
	public BorrowingRecord updateBook(BorrowingRecord record) {
		return recordRepo.save(record);
	}

	@Override
	public BorrowingRecord save(BorrowingRecord record) {
		return recordRepo.save(record);
	}

	@Override
	public List<BorrowingRecord> findByBookId(Long bookId) {
		return recordRepo.findByBookId(bookId);
	}

	@Override
	public List<BorrowingRecord> findByPatronId(Long patronId) {
		return recordRepo.findByPatronId(patronId);
	}

	@Override
	public List<BorrowingRecord> findByPatronIdAndBookId(Long patronId, Long bookId) {
		return recordRepo.findByPatronIdAndBookId(patronId, bookId);
	}

	@Override
	public void deleteAll(List<BorrowingRecord> records) {
		recordRepo.deleteAllInBatch(records);
		
	}

}
