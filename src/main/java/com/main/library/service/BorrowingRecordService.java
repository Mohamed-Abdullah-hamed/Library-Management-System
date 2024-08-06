package com.main.library.service;

import java.util.List;
import java.util.Optional;

import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.BorrowingRecordId;

public interface BorrowingRecordService {

	Optional<BorrowingRecord> findById(BorrowingRecordId id);
	List<BorrowingRecord> findAll();
	void deleteById(BorrowingRecordId id);
	BorrowingRecord updateBook(BorrowingRecord record);
	BorrowingRecord save(BorrowingRecord record);
	List<BorrowingRecord> findByBookId(Long bookId);
	List<BorrowingRecord> findByPatronId(Long patronId);

}
