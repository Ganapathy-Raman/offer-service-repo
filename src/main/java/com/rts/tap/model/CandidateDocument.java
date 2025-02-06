package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CandidateDocuments")
public class CandidateDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CandidateDocumentId;

	@Column
	private String payslip;

	@Column
	private String payslipStatus;

	@Column
	private String experienceLetter;

	@Column
	private String experienceLetterStatus;

	@Column
	private String degreeCertificate;
	@Column
	private String degreeCertificateStatus;
	@Column
	private String aadhar;
	@Column
	private String aadharStatus;
	@Column
	private String panCard;
	@Column
	private String panCardStatus;
	@Column
	private String passport;
	@Column
	private String passportStatus;
	@Column
	private String relievingLetter;
	@Column
	private String relievingLetterStatus;

}
