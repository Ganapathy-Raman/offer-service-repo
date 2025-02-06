package com.rts.tap.service;
 
import java.io.IOException;
import java.util.List;
 
import org.springframework.web.multipart.MultipartFile;
 
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.model.Offer;
 
public interface OfferService {
	Offer generateOfferLetter(OfferLetterDto offerLetterDto, MultipartFile offerLetter) throws IOException;
 
	Offer updateOfferLetter(Long offerId, OfferLetterDto offerLetterDto, MultipartFile offerLetter) throws IOException;
 
	Offer getOfferLetterByCandidateId(Long candidateId);
	
	List<Offer> getOfferLetterByRecruiterId(Long employeeId);
}