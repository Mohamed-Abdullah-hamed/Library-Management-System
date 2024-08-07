package com.main.library.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.BorrowingRecord;
import com.main.library.repo.BookRepo;
import com.main.library.repo.BorrowingRecordRepo;

public class BorrowingRecordServiceImplTest {

	@Mock
	private BookRepo bookRepo;
	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BorrowingRecordRepo recordRepo;
	@InjectMocks
	private BorrowingRecordServiceImpl recordServiceImpl;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveBorrowRecord() {
		// Arrange
		
		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Mockito.when(recordRepo.save(Mockito.any(BorrowingRecord.class))).thenReturn(record);
		// Act
		BorrowingRecord result = recordServiceImpl.save(record);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
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
		Mockito.when(recordRepo.findByPatronIdAndBookId(Mockito.anyLong(),Mockito.anyLong())).thenReturn(Arrays.asList(record));
		// Act
		List<BorrowingRecord> result = recordServiceImpl.findByPatronIdAndBookId(Mockito.anyLong(),Mockito.anyLong());
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
