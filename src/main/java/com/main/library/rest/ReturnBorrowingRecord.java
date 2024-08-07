package com.main.library.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.library.entity.Book;
import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.Patron;
import com.main.library.exception.CustomException;
import com.main.library.service.BookService;
import com.main.library.service.BorrowingRecordService;
import com.main.library.service.PatronService;

@RestController
@RequestMapping("api/return")
public class ReturnBorrowingRecord {

	@Autowired
	private BorrowingRecordService recordService;
	@Autowired
	private BookService bookService;
	@Autowired
	private PatronService patronService;

	@PutMapping("{bookId}/patron/{patronId}")
	public ResponseEntity<BorrowingRecord> updateBorrowingRecord(@PathVariable Long bookId,
			@PathVariable Long patronId) {

		Optional<Patron> patron = patronService.findById(patronId);
		if (patron.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		Optional<Book> book = bookService.findById(bookId);
		if (book.isEmpty()) {
			throw new CustomException("this Book is not found");
		}

		List<BorrowingRecord> patronRecords = recordService.findByPatronIdAndBookId(patronId, bookId);
		if (patronRecords.isEmpty()) {
			throw new CustomException("this Patron does not borrow this Book");
		}else if(patronRecords.get(0).getReturnDate() != null) {
			throw new CustomException(
					"this book had already returned before in day " + patronRecords.get(0).getReturnDate());
		}

		BorrowingRecord record = patronRecords.get(0);
		record.setReturnDate(LocalDate.now());
		record = recordService.save(record);
		return ResponseEntity.status(HttpStatus.CREATED).body(record);

	}
}
