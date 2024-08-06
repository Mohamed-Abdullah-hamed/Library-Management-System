package com.main.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.BorrowingRecordId;

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord,BorrowingRecordId>{

	@Query(value="select B from BorrowingRecord B where B.book.id =:bookId order by B.borrowingDate desc")
	List<BorrowingRecord> findByBookId(Long bookId);
	
	@Query(value="select B from BorrowingRecord B where B.patron.id =:patronId order by B.borrowingDate desc")
	List<BorrowingRecord> findByPatronId(Long patronId);
}
