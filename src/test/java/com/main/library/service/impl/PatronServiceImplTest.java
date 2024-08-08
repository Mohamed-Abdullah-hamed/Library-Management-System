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
import com.main.library.repo.PatronRepo;

public class PatronServiceImplTest {

	@Mock
	private PatronRepo patronRepo;

	@InjectMocks
	private PatronServiceImpl patronService;

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
		Mockito.when(recordServiceImpl.findByPatronId(Mockito.anyLong())).thenReturn(records);
		Mockito.doNothing().when(patronRepo).deleteById(Mockito.anyLong());
		
		assertThrows(CustomException.class, () -> {
			patronService.deleteById(Mockito.anyLong());
		});
	}

	@Test
	void testDeleteBook_Success() {
		List<BorrowingRecord> records = new ArrayList<>();
		Patron patron = new Patron();
		Mockito.when(patronRepo.findById(1L)).thenReturn(Optional.of(patron));
		Mockito.when(recordServiceImpl.findByPatronId(Mockito.anyLong())).thenReturn(records);
		Mockito.doNothing().when(patronRepo).deleteById(Mockito.anyLong());
		assertDoesNotThrow(() -> {
			patronService.deleteById(1L);
		});
	}

	@Test
	void testUpdatePatron_Success() {
		// Arrange
		Patron patron = new Patron();
		patron.setId(1L);
		Mockito.when(patronRepo.findById(1L)).thenReturn(Optional.of(patron));
		Mockito.when(patronRepo.save(Mockito.any(Patron.class))).thenReturn(patron);
		
		// Act
		Patron result = patronService.updatePatron(patron,1L);

		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}
	@Test
	void testUpdatePatron_Failure() {
		// Arrange
		Patron patron = new Patron();
		patron.setId(1L);
		Mockito.when(patronRepo.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(patronRepo.save(patron)).thenReturn(patron);
		
		assertThrows(CustomException.class, () -> {
			patronService.updatePatron(patron,1L);
		});
	}

	@Test
	void testGetAllPatrons() {
		// Arrange
		Patron patron = new Patron();
		List<Patron> pts = Arrays.asList(patron);
		Mockito.when(patronRepo.findAll()).thenReturn(pts);
		// Act
		List<Patron> result = patronService.findAll();
		// Assert
		assertEquals(1, result.size());
	}

	@Test
	void testSavePatron() {
		// Arrange
		Patron patron = new Patron();
		patron.setId(1L);
		Mockito.when(patronRepo.save(Mockito.any(Patron.class))).thenReturn(patron);
		// Act
		Patron result = patronService.save(patron);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void testGetPatronById() {
		// Arrange
		Patron patron = new Patron();
		patron.setId(1L);
		Mockito.when(patronRepo.findById(1L)).thenReturn(Optional.of(patron));
		// Act
		Optional<Patron> result = patronService.findById(1L);
		// Assert
		assertNotNull(result);
		assertEquals(1L, result.get().getId());
	}
}
