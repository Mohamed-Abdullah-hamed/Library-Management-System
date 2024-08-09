package com.main.library.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.main.library.entity.BorrowingRecord;
import com.main.library.service.impl.BorrowingRecordServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowingRecordRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BorrowingRecordServiceImpl recordService;

	@Test
	@WithMockUser(username = "testadmin", roles = { "ADMIN" })
	void testAddBorrowingRecord() throws Exception {

		BorrowingRecord record = new BorrowingRecord();
		record.setId(1L);
		when(recordService.save(1L, 1L)).thenReturn(record);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/borrow/1/patron/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

}
