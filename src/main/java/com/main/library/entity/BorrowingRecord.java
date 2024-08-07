package com.main.library.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JsonIgnoreProperties(value = {"borrowingRecords"})
	private Book book;
	@ManyToOne
	@JsonIgnoreProperties(value = {"borrowingRecords"})
	private Patron patron;

	private LocalDate borrowingDate;
	private LocalDate returnDate;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
