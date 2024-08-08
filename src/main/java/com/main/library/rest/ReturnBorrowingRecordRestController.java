package com.main.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.library.entity.BorrowingRecord;
import com.main.library.service.BorrowingRecordService;

@RestController
@RequestMapping("api/return")
public class ReturnBorrowingRecordRestController {

	@Autowired
	private BorrowingRecordService recordService;

	@PutMapping("{bookId}/patron/{patronId}")
	public ResponseEntity<BorrowingRecord> updateBorrowingRecord(@PathVariable Long bookId,
			@PathVariable Long patronId) {

		BorrowingRecord record = recordService.update(bookId, patronId);
		return ResponseEntity.status(HttpStatus.CREATED).body(record);

	}
}
