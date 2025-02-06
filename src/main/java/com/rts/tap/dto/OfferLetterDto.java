package com.rts.tap.dto;

import java.sql.Date;

public class OfferLetterDto {
	private Double offerPackage;
	private Date joiningDate;
	private Long mrfId;
	private Long candidateId;

	public OfferLetterDto() {
		super();
	}

	public Double getOfferPackage() {
		return offerPackage;
	}

	public void setOfferPackage(Double offerPackage) {
		this.offerPackage = offerPackage;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Long getMrfId() {
		return mrfId;
	}

	public void setMrfId(Long mrfId) {
		this.mrfId = mrfId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public void setJoiningDate(String string) {		
	}

	public void setOfferPackage(String string) {		
	}

}
