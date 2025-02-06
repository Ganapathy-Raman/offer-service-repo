package com.rts.tap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "qualifications")
public class Qualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qualification_id")
	private Long qualificationId;

	@Column(name = "typeOfEducation")
	private String typeOfEducation;

	@Column(name = "institution")
	private String institutionName;
		
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "from_year")
    private LocalDateTime fromYear;
	
	@Column(name = "year_of_completion")
    private LocalDateTime yearOfCompletion;

	@Column(name = "grade")
	private String grade;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Lob
	@Column(length = 1000000)
	private byte[] certificates;

	public enum TypeOfEducation {
		SSLC, HSC, DIPLOMA, PG, UG
	}

	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = LocalDateTime.now(); // Only update updatedDate on update
	}
}
