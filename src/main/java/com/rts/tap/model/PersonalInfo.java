package com.rts.tap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "personal_information")
public class PersonalInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personal_info_id")
	private Long personalInfoId;

	@Column(name = "employee_first_name")
	private String firstName;
	
	@Column(name = "personalEmail")
	private String personalEmail;

	@Column(name = "employee_last_name")
	private String lastName;

	@Column(name = "employee_phone")
	private String phone;

	// Change dateOfBirth from LocalDate to LocalDateTime
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // Format to match ISO 8601 format
	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "employee_address")
	private String employeeAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "postal_code")
	private String pincode;

	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "district")
	private String district;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Lob
	@Column(length = 1000000)
	private byte[] employeePhoto;

	public enum Gender {
		MALE, FEMALE, OTHER
	}
}