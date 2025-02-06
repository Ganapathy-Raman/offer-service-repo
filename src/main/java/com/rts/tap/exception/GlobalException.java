package com.rts.tap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends Throwable {

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<String> handleOfferNotFoundException(OfferNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<String> handleCandidateNotFoundException(CandidateNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(MrfNotFoundException.class)
    public ResponseEntity<String> handleMrfNotFoundException(MrfNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(OfferApprovalNotFoundException.class)
    public ResponseEntity<String> handleOfferApprovalNotFoundException(OfferApprovalNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(OfferLetterServiceException.class)
    public ResponseEntity<String> handleOfferLetterService(OfferLetterServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	@ExceptionHandler(ApprovarNotFoundException.class)
    public ResponseEntity<String> handleApprovarNotFoundException(ApprovarNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
