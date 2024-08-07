package com.main.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.library.entity.Book;
import com.main.library.entity.BorrowingRecord;
import com.main.library.exception.CustomException;
import com.main.library.repo.BookRepo;
import com.main.library.service.BookService;
import com.main.library.service.BorrowingRecordService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private BorrowingRecordService recordService;

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepo.findById(id);
	}

	@Override
	public List<Book> findAll() {
		return bookRepo.findAll();
	}

	@Override
	public void deleteById(Long id) {
		List<BorrowingRecord> records = recordService.findByBookId(id);
		if (records.size() > 0 && records.get(0).getReturnDate() == null) {
			throw new CustomException("this Book already Borrowed to " + records.get(0).getPatron().getName());
		}
		if (!records.isEmpty()) {
			recordService.deleteAll(records);
		}
		bookRepo.deleteById(id);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public Book save(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public Book findByIsbn(String isbn) {
		return bookRepo.findByIsbn( isbn);
	}

}
