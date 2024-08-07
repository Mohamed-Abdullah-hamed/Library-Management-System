package com.main.library.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("api/borrow")
public class BorrowingRecordRestController {

	@Autowired
	private BorrowingRecordService recordService;
	@Autowired
	private BookService bookService;
	@Autowired
	private PatronService patronService;

	@PostMapping("{bookId}/patron/{patronId}")
	public ResponseEntity<BorrowingRecord> addBorrowingRecord(@PathVariable Long bookId,@PathVariable Long patronId) {
		Optional<Patron> patron = patronService.findById(patronId);
		if(patron.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		Optional<Book> book = bookService.findById(bookId);
		if(book.isEmpty()) {
			throw new CustomException("this Book is not found");
		}
		// check if the patron already have a book and not return it yet
		List<BorrowingRecord> patronRecords = recordService.findByPatronId(patronId);
		if(patronRecords.size() > 0 && patronRecords.get(0).getReturnDate() == null ) {
			throw new CustomException("this patron already has a book and not return it yet");
		}
		List<BorrowingRecord> bookRecords = recordService.findByBookId(bookId);
		if(bookRecords.size() > 0 && bookRecords.get(0).getReturnDate() == null ) {
			throw new CustomException("this book already has been borrowed and has not return yet");
		}
		BorrowingRecord record = new BorrowingRecord();
		record.setBook(book.get());
		record.setPatron(patron.get());
		record.setBorrowingDate(LocalDate.now());
		record = recordService.save(record);
		return ResponseEntity.status(HttpStatus.CREATED).body(record);
		
	}
	
}
