package com.main.library.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.library.annotation.ValidIdentityNumber;
import com.main.library.annotation.ValidMobileNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Length(min = 4)
	private String name;
	
	@ValidMobileNumber
	@Column(name = "phone",unique = true)
	private String phoneNumber;
	@ValidIdentityNumber
	@Column(name = "identity",unique = true)
	private String identityNumber;
	
	@OneToMany(mappedBy = "patron")
//	@JsonIgnoreProperties(value = {"patron"})
	@JsonIgnore
	private List<BorrowingRecord> borrowingRecords = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<BorrowingRecord> getBorrowingRecords() {
		return borrowingRecords;
	}
	public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
		this.borrowingRecords = borrowingRecords;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	@Override
	public String toString() {
		return "Patron [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", identityNumber="
				+ identityNumber + ", borrowingRecords=" + borrowingRecords + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, identityNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patron other = (Patron) obj;
		return Objects.equals(id, other.id) && Objects.equals(identityNumber, other.identityNumber);
	}
	
	
	
}
