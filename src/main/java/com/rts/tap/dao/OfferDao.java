package com.rts.tap.dao;
 
import java.util.List;

import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;
 
public interface OfferDao {
 
	Offer findOfferByCandidateId(Long candidateId);
	
	List<Offer> findOfferByRecruiterId(Long employeeId, List<Candidate> candidates);
 
	Offer updateOfferLetter(Offer offer);
 
	void saveOffer(Offer offer);
 
	Offer findByOfferId(Long offerId);
	
	
 
}