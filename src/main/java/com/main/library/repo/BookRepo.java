package com.main.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.library.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

	Book findByIsbn(String isbn);

}
