package com.main.library.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.library.entity.Patron;
import com.main.library.exception.CustomException;
import com.main.library.service.PatronService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/patrons")
public class PatronRestController {
	@Autowired
	private PatronService patronService;

	@GetMapping("{id}")
	public ResponseEntity<Patron> findById(@PathVariable Long id) {
		Optional<Patron> patron = patronService.findById(id);
		if (patron.isPresent()) {
			return ResponseEntity.ok(patron.get());
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping()
	public ResponseEntity<List<Patron>> findAll() {
		List<Patron> patrons = patronService.findAll();
		if (patrons.size() == 0)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(patrons);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Optional<Patron> patron = patronService.findById(id);
		if (patron.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		patronService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Patron> updateBook(@RequestBody @Valid Patron patron, @PathVariable Long id) {
		if (id == null || id == 0) {
			return ResponseEntity.badRequest().build();
		}
		Optional<Patron> b = patronService.findById(id);
		if (b.isEmpty()) {
			throw new CustomException("this patron is not found");
		}
		patron.setId(id);
		Patron updatedPatron = patronService.updatePatron(patron);
		return ResponseEntity.ok(updatedPatron);
	}

	@PostMapping
	public ResponseEntity<Patron> save(@RequestBody @Valid Patron patron) {
		if (patron.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		Patron savedBook = patronService.save(patron);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}

}
