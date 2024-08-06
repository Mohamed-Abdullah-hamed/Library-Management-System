package com.main.library.service;

import java.util.List;
import java.util.Optional;

import com.main.library.entity.Book;

public interface BookService {

	Optional<Book> findById(Long id);
	List<Book> findAll();
	void deleteById(Long id);
	Book updateBook(Book book);
	Book save(Book book);
}
