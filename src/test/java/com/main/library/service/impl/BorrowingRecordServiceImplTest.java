package com.main.library.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.main.library.repo.BorrowingRecordRepo;

public class BorrowingRecordServiceImplTest {

	@Mock
	private BookServiceImpl bookService;
	@Mock
	private PatronServiceImpl patronService;
	@Mock
	private BorrowingRecordRepo recordRepo;
	@InjectMocks
	private BorrowingRecordServiceImpl recordServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testUpdateBorrowRecord_Success() {
		// Arrange

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		ArrayList<BorrowingRecord> records = new ArrayList<>();
		records.add(record);
		Optional<Patron> patronOpt = Optional.of(new Patron());
		Optional<Book> bookOpt = Optional.of(new Book());
		Mockito.when(patronService.findById(1L)).thenReturn(patronOpt);
		Mockito.when(bookService.findById(1L)).thenReturn(bookOpt);
		Mockito.when(recordRepo.findByPatronIdAndBookId(1L, 1L)).thenReturn(records);
		Mockito.when(recordRepo.save(Mockito.any())).thenReturn(record);

		// Act
		BorrowingRecord result = recordServiceImpl.save(1L, 1L);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void testUpdateBorrowRecord_Failure() {
		// Arrange

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);

		Optional<Patron> patronOpt = Optional.empty();
		Optional<Book> bookOpt = Optional.empty();
		Mockito.when(patronService.findById(1L)).thenReturn(patronOpt);
		Mockito.when(bookService.findById(1L)).thenReturn(bookOpt);
		Mockito.when(recordRepo.findByPatronIdAndBookId(1L, 1L)).thenReturn(new ArrayList<>());
		Mockito.when(recordRepo.save(Mockito.any())).thenReturn(record);

		// Act
		assertThrows(CustomException.class, () -> {
			recordServiceImpl.save(1L, 1L);
		});
	}

	@Test
	void testSaveBorrowRecord_Success() {
		// Arrange

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Optional<Patron> patronOpt = Optional.of(new Patron());
		Optional<Book> bookOpt = Optional.of(new Book());
		Mockito.when(patronService.findById(1L)).thenReturn(patronOpt);
		Mockito.when(bookService.findById(1L)).thenReturn(bookOpt);
		Mockito.when(recordRepo.findByPatronId(1L)).thenReturn(new ArrayList<>());
		Mockito.when(recordRepo.findByBookId(1L)).thenReturn(new ArrayList<>());
		Mockito.when(recordRepo.save(Mockito.any())).thenReturn(record);

		// Act
		BorrowingRecord result = recordServiceImpl.save(1L, 1L);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void testSaveBorrowRecord_Failure() {
		// Arrange

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Optional<Patron> patronOpt = Optional.empty();
		Optional<Book> bookOpt = Optional.empty();
		Mockito.when(patronService.findById(1L)).thenReturn(patronOpt);
		Mockito.when(bookService.findById(1L)).thenReturn(bookOpt);
		Mockito.when(recordRepo.findByPatronId(1L)).thenReturn(new ArrayList<>());
		Mockito.when(recordRepo.findByBookId(1L)).thenReturn(new ArrayList<>());
		Mockito.when(recordRepo.save(Mockito.any())).thenReturn(record);

		assertThrows(CustomException.class, () -> {
			recordServiceImpl.save(1L, 1L);
		});
	}

	@Test
	void testFindByPatronId() {
		// Arrange
		BorrowingRecord record = new BorrowingRecord();
		Mockito.when(recordRepo.findByPatronId(Mockito.anyLong())).thenReturn(Arrays.asList(record));
		// Act
		List<BorrowingRecord> result = recordServiceImpl.findByPatronId(Mockito.anyLong());
		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	void testFindByPatronIdAndBookId() {
		// Arrange
		BorrowingRecord record = new BorrowingRecord();
		Mockito.when(recordRepo.findByPatronIdAndBookId(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(Arrays.asList(record));
		// Act
		List<BorrowingRecord> result = recordServiceImpl.findByPatronIdAndBookId(Mockito.anyLong(), Mockito.anyLong());
		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	void testFindByBookId() {
		// Arrange
		BorrowingRecord record = new BorrowingRecord();
		Mockito.when(recordRepo.findByBookId(Mockito.anyLong())).thenReturn(Arrays.asList(record));
		// Act
		List<BorrowingRecord> result = recordServiceImpl.findByBookId(Mockito.anyLong());
		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	void testDeleteAll() {
		Mockito.doNothing().when(recordRepo).deleteAllInBatch(Mockito.anyIterable());
		assertDoesNotThrow(() -> {
			recordServiceImpl.deleteAll(Mockito.anyList());
		});
	}
}
