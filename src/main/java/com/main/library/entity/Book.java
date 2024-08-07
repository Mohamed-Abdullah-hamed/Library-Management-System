package com.main.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.main.library.annotation.YearBeforeCurrentYear;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Length(min = 4)
	private String title;
	@NotBlank
	@Length(min = 4)
	private String author;
	@YearBeforeCurrentYear
	@Column(name = "publication_year")
	private Integer publicationYear;
	@ISBN
	@NotBlank
	@Column(unique = true)
	private String isbn;

	@OneToMany(mappedBy = "book")
//	@JsonIgnoreProperties(value = {"book"})
	@JsonIgnore
	private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<BorrowingRecord> getBorrowingRecords() {
		return borrowingRecords;
	}

	public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
		this.borrowingRecords = borrowingRecords;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
				+ ", isbn=" + isbn + "]";
	}

}
