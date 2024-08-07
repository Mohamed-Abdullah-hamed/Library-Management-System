package com.main.library.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
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

@WebMvcTest(ReturnBorrowingRecordRestController.class)
public class ReturnBorrowingRecordRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BorrowingRecordServiceImpl recordService;
	@MockBean
	private PatronServiceImpl patronService;
	@MockBean
	private BookServiceImpl bookService;

	@Test
	void testUpdateBorrowingRecord_Success() throws Exception {

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Patron patron = new Patron();
		Optional<Patron> optPatron = Optional.of(patron);
		Book book = new Book();
		Optional<Book> optBook = Optional.of(book);
		List<BorrowingRecord> records = new ArrayList<>();
		records.add(record);
		
		when(bookService.findById(1L)).thenReturn(optBook);
		when(patronService.findById(1L)).thenReturn(optPatron);
		when(recordService.findByPatronIdAndBookId(1L,1L)).thenReturn(records);
		when(recordService.save(Mockito.any())).thenReturn(record);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/return/1/patron/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	void testUpdateBorrowingRecord_Failure() throws Exception {

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		Optional<Patron> optPatron = Optional.empty();
		Optional<Book> optBook = Optional.empty();

		when(bookService.findById(1L)).thenReturn(optBook);
		when(patronService.findById(1L)).thenReturn(optPatron);
		when(recordService.findByPatronIdAndBookId(1L,1L)).thenReturn(new ArrayList<>());
		when(recordService.save(Mockito.any())).thenReturn(record);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/return/1/patron/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
