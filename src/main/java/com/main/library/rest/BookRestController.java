package com.main.library.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.library.entity.Book;
import com.main.library.entity.BorrowingRecord;
import com.main.library.service.BookService;
import com.main.library.service.BorrowingRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/books")
public class BookRestController {
	@Autowired
	private BookService bookService;
	@Autowired
	private BorrowingRecordService recordService;

	@GetMapping("{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Optional<Book> book = bookService.findById(id);
		if (book.isPresent()) {
			return ResponseEntity.ok(book.get());
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping()
	public ResponseEntity<List<Book>> findAll() {
		List<Book> books = bookService.findAll();
		if (books.size() == 0)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(books);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Optional<Book> book = bookService.findById(id);
		if (book.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		List<BorrowingRecord> records = recordService.findByBookId(id);
		if (records.size() > 0 && records.get(0).getReturnDate() != null
				&& records.get(0).getReturnDate().isAfter(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		bookService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book, @PathVariable Long id) {
		if (id == null || id.equals(0)) {
			return ResponseEntity.badRequest().build();
		}
		Optional<Book> b = bookService.findById(id);
		if(b.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		book.setId(id);
		Book updatedBook = bookService.updateBook(book);
		return ResponseEntity.ok(updatedBook);
	}

	@PostMapping
	public ResponseEntity<Book> save(@RequestBody @Valid Book book) {
		if (book.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		Book savedBook = bookService.save(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}

}
