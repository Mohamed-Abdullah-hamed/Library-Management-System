package com.main.library.service;

import java.util.List;
import java.util.Optional;

import com.main.library.entity.BorrowingRecord;

public interface BorrowingRecordService {

	Optional<BorrowingRecord> findById(Long id);

	List<BorrowingRecord> findAll();

	void deleteById(Long id);

	BorrowingRecord updateBook(BorrowingRecord record);

	BorrowingRecord save(BorrowingRecord record);

	List<BorrowingRecord> findByBookId(Long bookId);

	List<BorrowingRecord> findByPatronId(Long patronId);

	List<BorrowingRecord> findByPatronIdAndBookId(Long patronId, Long bookId);

	void deleteAll(List<BorrowingRecord> records);

}
