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
import com.main.library.entity.Patron;
import com.main.library.service.impl.PatronServiceImpl;

@WebMvcTest(PatronRestController.class)
public class PatronRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatronServiceImpl patronService;

	@Test
	void testFindById() throws Exception {

		Patron patron = new Patron();
		patron.setId(1L);
		Optional<Patron> opt = Optional.of(patron);
		when(patronService.findById(1L)).thenReturn(opt);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	void testUpdatePatron_Success() throws Exception {

		Patron patron = new Patron();
		patron.setId(1L);
		patron.setIdentityNumber("784-1244-1234567-2");
		patron.setPhoneNumber("501334562");
		patron.setName("Mohamed");
		Optional<Patron> opt = Optional.of(patron);
	
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(patron);
		when(patronService.findById(1L)).thenReturn(opt);
		when(patronService.updatePatron(Mockito.any())).thenReturn(patron);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/1").contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	void testSave_Success() throws Exception {

		Patron patron = new Patron();
		patron.setIdentityNumber("784-1244-1234567-2");
		patron.setPhoneNumber("501334562");
		patron.setName("Mohamed");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(patron);
		when(patronService.save(Mockito.any())).thenReturn(patron);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/patrons").contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("501334562"));
	}

	@Test
	void testSaveWithUnValidBody() throws Exception {

		Patron patron = new Patron();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(patron);

		when(patronService.save(Mockito.any())).thenReturn(patron);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/patrons").contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void testUpdatePatronWithUnValidBody() throws Exception {

		Patron patron = new Patron();
		patron.setId(1L);
		Optional<Patron> opt = Optional.of(patron);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(patron);
		when(patronService.findById(1L)).thenReturn(opt);
		when(patronService.updatePatron(Mockito.any())).thenReturn(patron);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/1").contentType(MediaType.APPLICATION_JSON)
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	void testDeleteById_Success() throws Exception {

		Patron patron = new Patron();
		patron.setId(1L);
		Optional<Patron> opt = Optional.of(patron);
		when(patronService.findById(1L)).thenReturn(opt);
		doNothing().when(patronService).deleteById(1L);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testDeleteById_Failure() throws Exception {

		Optional<Patron> opt = Optional.empty();
		when(patronService.findById(1L)).thenReturn(opt);
		doNothing().when(patronService).deleteById(1L);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	void testFindAll() throws Exception {

		Patron patron = new Patron();
		List<Patron> patrons = new ArrayList<>();
		patrons.add(patron);
		when(patronService.findAll()).thenReturn(patrons);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
