package com.main.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.library.entity.BorrowingRecord;

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord, Long> {

	@Query(value = "select B from BorrowingRecord B where B.book.id =:bookId order by B.id desc")
	List<BorrowingRecord> findByBookId(Long bookId);

	@Query(value = "select B from BorrowingRecord B where B.patron.id =:patronId order by B.id desc")
	List<BorrowingRecord> findByPatronId(Long patronId);
	@Query(value = "select B from BorrowingRecord B where B.patron.id =:patronId and B.book.id =:bookId order by B.id desc")
	List<BorrowingRecord> findByPatronIdAndBookId(Long patronId,Long bookId);
}
