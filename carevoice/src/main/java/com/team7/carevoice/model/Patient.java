package com.team7.carevoice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generated unique ID
    private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private CareVoiceUser carevoiceuser;

	@Column(nullable = false, unique = true) // Ensure AHS number is unique and non-null
    private String ahsNumber;
    private String name;
    private Date dateOfBirth;

	public Patient() {

	}

	public Patient(String ahsNumber, String name, Date dateOfBirth, CareVoiceUser user) {
		this.ahsNumber = ahsNumber;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.carevoiceuser = user;
	}

	public long getId() {
		return id;
	}

	public String getAhsNumber() {
		return ahsNumber;
	}

	public void setAhsNumber(String ahsNumber) {
		this.ahsNumber = ahsNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
