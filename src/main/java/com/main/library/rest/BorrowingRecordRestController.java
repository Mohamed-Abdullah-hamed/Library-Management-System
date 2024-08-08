package com.main.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.library.entity.BorrowingRecord;
import com.main.library.service.BorrowingRecordService;

@RestController
@RequestMapping("api/borrow")
public class BorrowingRecordRestController {

	@Autowired
	private BorrowingRecordService recordService;

	@PostMapping("{bookId}/patron/{patronId}")
	public ResponseEntity<BorrowingRecord> addBorrowingRecord(@PathVariable Long bookId, @PathVariable Long patronId) {

		BorrowingRecord record = recordService.save(bookId, patronId);
		return ResponseEntity.status(HttpStatus.CREATED).body(record);

	}

}
