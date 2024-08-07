package com.main.library.service;

import java.util.List;

import com.main.library.entity.BorrowingRecord;

public interface BorrowingRecordService {


	BorrowingRecord save(BorrowingRecord record);

	List<BorrowingRecord> findByBookId(Long bookId);

	List<BorrowingRecord> findByPatronId(Long patronId);

	List<BorrowingRecord> findByPatronIdAndBookId(Long patronId, Long bookId);

	void deleteAll(List<BorrowingRecord> records);

}
