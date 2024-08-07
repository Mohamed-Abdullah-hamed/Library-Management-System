package com.main.library.rest;

import static org.mockito.Mockito.doNothing;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.library.entity.Book;
import com.main.library.service.impl.BookServiceImpl;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookServiceImpl bookService;

	@Test
	void testFindById() throws Exception {

		Book book = new Book();
		book.setId(1L);
		Optional<Book> opt = Optional.of(book);
		when(bookService.findById(1L)).thenReturn(opt);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	void testUpdateBook_Success() throws Exception {

		Book book = new Book();
		book.setId(1L);
		book.setPublicationYear(2023);
		book.setIsbn("978-3-16-148410-0");
		book.setAuthor("abcd");
		book.setTitle("abcd");
		Optional<Book> opt = Optional.of(book);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(book);
		when(bookService.findById(1L)).thenReturn(opt);
		when(bookService.findByIsbn(Mockito.anyString())).thenReturn(null);
		when(bookService.updateBook(Mockito.any())).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	void testSave_Success() throws Exception {

		Book book = new Book();
		book.setPublicationYear(2023);
		book.setIsbn("978-3-16-148410-0");
		book.setAuthor("abcd");
		book.setTitle("abcd");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(book);
		
		when(bookService.findByIsbn(Mockito.anyString())).thenReturn(null);
		when(bookService.save(Mockito.any())).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/books").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.author").value("abcd"));
	}
	@Test
	void testSaveWithUnValidBody() throws Exception {

		Book book = new Book();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(book);
		
		when(bookService.findByIsbn(Mockito.anyString())).thenReturn(null);
		when(bookService.save(Mockito.any())).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/books").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateBookWithUnValidBody() throws Exception {

		Book book = new Book();
		book.setId(1L);
		Optional<Book> opt = Optional.of(book);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(book);
		when(bookService.findById(1L)).thenReturn(opt);
		when(bookService.findByIsbn(Mockito.anyString())).thenReturn(null);
		when(bookService.updateBook(Mockito.any())).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void testDeleteById_Success() throws Exception {

		Book book = new Book();
		book.setId(1L);
		Optional<Book> opt = Optional.of(book);
		when(bookService.findById(1L)).thenReturn(opt);
		doNothing().when(bookService).deleteById(1L);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testDeleteById_Failure() throws Exception {

		Optional<Book> opt = Optional.empty();
		when(bookService.findById(1L)).thenReturn(opt);
		doNothing().when(bookService).deleteById(1L);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testFindAll() throws Exception {

		Book book = new Book();
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookService.findAll()).thenReturn(books);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/books").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
