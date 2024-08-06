package com.main.library.entity;

import java.io.Serializable;
import java.util.Objects;

public class BorrowingRecordId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1908390481869884251L;

	private Long book;
	private Long patron;

	public BorrowingRecordId() {
		super();
	}

	public BorrowingRecordId(Long book, Long patron) {
		super();
		this.book = book;
		this.patron = patron;
	}

	public Long getBook() {
		return book;
	}

	public void setBook(Long book) {
		this.book = book;
	}

	public Long getPatron() {
		return patron;
	}

	public void setPatron(Long patron) {
		this.patron = patron;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, patron);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BorrowingRecordId other = (BorrowingRecordId) obj;
		return Objects.equals(book, other.book) && Objects.equals(patron, other.patron);
	}

	@Override
	public String toString() {
		return "BorrowingRecordId [book=" + book + ", patron=" + patron + "]";
	}

	
	

}
