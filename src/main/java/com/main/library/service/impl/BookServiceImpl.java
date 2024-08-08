package com.main.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void deleteById(Long id) {
		Optional<Book> book = findById(id);
		if (book.isEmpty()) {
			throw new CustomException("this Book is not found");
		}
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
	@Transactional
	public Book updateBook(Book book, Long id) {
		if (id == null || id == 0) {
			return null;
		}
		Optional<Book> b = findById(id);
		if (b.isEmpty()) {
			throw new CustomException("this Book is not found");
		}
		Book b2 = findByIsbn(book.getIsbn());
		if (b2 != null && !id.equals(b2.getId())) {
			throw new CustomException("Duplicate Isbn -> this Isbn is inserted before ");
		}
		book.setId(id);
		Book updatedBook = bookRepo.save(book);
		return updatedBook;
	}

	@Override
	@Transactional
	public Book save(Book book) {
		Book b = findByIsbn(book.getIsbn());
		if(b != null) {
			throw new CustomException("Duplicate Isbn -> this Isbn is inserted before ");
		}
		return bookRepo.save(book);
	}

	@Override
	public Book findByIsbn(String isbn) {
		return bookRepo.findByIsbn( isbn);
	}

}
