package com.rts.tap.exception;

public class CandidateNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	String message;

	public CandidateNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
