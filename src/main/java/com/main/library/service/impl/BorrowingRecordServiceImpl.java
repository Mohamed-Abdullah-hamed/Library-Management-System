package com.main.library.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.main.library.entity.Book;
import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.Patron;
import com.main.library.exception.CustomException;
import com.main.library.repo.BorrowingRecordRepo;
import com.main.library.service.BookService;
import com.main.library.service.BorrowingRecordService;
import com.main.library.service.PatronService;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

	@Autowired
	private BorrowingRecordRepo recordRepo;
	@Autowired
	@Lazy
	private PatronService patronService;
	@Autowired
	@Lazy
	private BookService bookService;

	@Override
	public BorrowingRecord update(Long bookId, Long patronId) {
		Optional<Patron> patron = patronService.findById(patronId);
		if (patron.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		Optional<Book> book = bookService.findById(bookId);
		if (book.isEmpty()) {
			throw new CustomException("this Book is not found");
		}

		List<BorrowingRecord> patronRecords = recordRepo.findByPatronIdAndBookId(patronId, bookId);
		if (patronRecords.isEmpty()) {
			throw new CustomException("this Patron does not borrow this Book");
		} else if (patronRecords.get(0).getReturnDate() != null) {
			throw new CustomException(
					"this book had already returned before in day " + patronRecords.get(0).getReturnDate());
		}

		BorrowingRecord record = patronRecords.get(0);
		record.setReturnDate(LocalDate.now());
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

	@Override
	public BorrowingRecord save(Long bookId, Long patronId) {
		Optional<Patron> patron = patronService.findById(patronId);
		if (patron.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		Optional<Book> book = bookService.findById(bookId);
		if (book.isEmpty()) {
			throw new CustomException("this Book is not found");
		}
		// check if the patron already have a book and not return it yet
		List<BorrowingRecord> patronRecords =recordRepo.findByPatronId(patronId);
		if (patronRecords.size() > 0 && patronRecords.get(0).getReturnDate() == null) {
			throw new CustomException("this patron already has a book and not return it yet");
		}
		List<BorrowingRecord> bookRecords = recordRepo.findByBookId(bookId);
		if (bookRecords.size() > 0 && bookRecords.get(0).getReturnDate() == null) {
			throw new CustomException("this book already has been borrowed and has not return yet");
		}
		BorrowingRecord record = new BorrowingRecord();
		record.setBook(book.get());
		record.setPatron(patron.get());
		record.setBorrowingDate(LocalDate.now());
		record = recordRepo.save(record);
		return record;
	}

}
