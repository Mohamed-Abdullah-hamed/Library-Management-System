package com.main.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.library.entity.Book;
import com.main.library.repo.BookRepo;
import com.main.library.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepo bookRepo;
	
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

}
