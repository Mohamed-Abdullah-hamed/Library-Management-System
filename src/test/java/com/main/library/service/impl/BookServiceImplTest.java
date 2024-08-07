package com.main.library.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.main.library.entity.Book;
import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.Patron;
import com.main.library.exception.CustomException;
import com.main.library.repo.BookRepo;

public class BookServiceImplTest {

	@Mock
	private BookRepo bookRepo;
	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BorrowingRecordServiceImpl recordServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testDeleteBook_Failure() {
		BorrowingRecord record = new BorrowingRecord();
		record.setPatron(new Patron());
		record.setBook(new Book());

		List<BorrowingRecord> records = Arrays.asList(record);
		Mockito.when(recordServiceImpl.findByBookId(Mockito.anyLong())).thenReturn(records);

		assertThrows(CustomException.class, () -> {
			bookService.deleteById(Mockito.anyLong());
		});
	}

	@Test
	void testDeleteBook_Success() {
		List<BorrowingRecord> records = new ArrayList<>();
		Mockito.when(recordServiceImpl.findByBookId(Mockito.anyLong())).thenReturn(records);
		Mockito.doNothing().when(bookRepo).deleteById(Mockito.anyLong());
		assertDoesNotThrow(() -> {
			bookService.deleteById(Mockito.anyLong());
		});
	}

	@Test
	void testUpdateBook() {
		// Arrange
		Book book = new Book();
		book.setId(1L);
		Mockito.when(bookRepo.save(Mockito.any(Book.class))).thenReturn(book);

		// Act
		Book result = bookService.updateBook(book);

		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void testSaveBook() {
		// Arrange
		Book book = new Book();
		book.setId(1L);
		Mockito.when(bookRepo.save(Mockito.any(Book.class))).thenReturn(book);
		// Act
		Book result = bookService.save(book);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	public void testFindByIsbn_Success() {
		Book book = new Book();
		book.setId(1L);
		book.setIsbn("978-3-16-148410-0");
		Mockito.when(bookRepo.findByIsbn("978-3-16-148410-0")).thenReturn(book);
		Book result = bookService.findByIsbn("978-3-16-148410-0");
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	public void testFindByIsbn_Failure() {

		Mockito.when(bookRepo.findByIsbn("978-3-16-148410-0")).thenReturn(null);
		Book result = bookService.findByIsbn("978-3-16-148410-0");
		assertNull(result);
	}

	@Test
	void testGetUBookById() {
		// Arrange
		Book book = new Book();
		book.setId(1L);
		Mockito.when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
		// Act
		Optional<Book> result = bookService.findById(1L);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.get().getId());
	}

	@Test
	void testGetAllBooks() {
		// Arrange
		Book book = new Book();
		List<Book> pts = Arrays.asList(book);
		Mockito.when(bookRepo.findAll()).thenReturn(pts);
		// Act
		List<Book> result = bookService.findAll();
		// Assert
		assertEquals(1, result.size());
	}
}
