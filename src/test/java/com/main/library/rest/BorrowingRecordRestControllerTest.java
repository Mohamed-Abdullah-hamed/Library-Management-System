package com.main.library.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.main.library.entity.Book;
import com.main.library.entity.BorrowingRecord;
import com.main.library.entity.Patron;
import com.main.library.service.impl.BookServiceImpl;
import com.main.library.service.impl.BorrowingRecordServiceImpl;
import com.main.library.service.impl.PatronServiceImpl;

@WebMvcTest(BorrowingRecordRestController.class)
public class BorrowingRecordRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BorrowingRecordServiceImpl recordService;
	@MockBean
	private PatronServiceImpl patronService;
	@MockBean
	private BookServiceImpl bookService;

	@Test
	void testAddBorrowingRecord_Success() throws Exception {

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Patron patron = new Patron();
		Optional<Patron> optPatron = Optional.of(patron);
		Book book = new Book();
		Optional<Book> optBook = Optional.of(book);

		when(bookService.findById(1L)).thenReturn(optBook);
		when(patronService.findById(1L)).thenReturn(optPatron);
		when(recordService.findByPatronId(1L)).thenReturn(new ArrayList<>());
		when(recordService.findByBookId(1L)).thenReturn(new ArrayList<>());
		when(recordService.save(Mockito.any())).thenReturn(record);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	void testAddBorrowingRecord_Failure() throws Exception {

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Optional<Patron> optPatron = Optional.empty();
		Optional<Book> optBook = Optional.empty();

		when(bookService.findById(1L)).thenReturn(optBook);
		when(patronService.findById(1L)).thenReturn(optPatron);
		when(recordService.findByPatronId(1L)).thenReturn(new ArrayList<>());
		when(recordService.findByBookId(1L)).thenReturn(new ArrayList<>());
		when(recordService.save(Mockito.any())).thenReturn(record);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
