package com.rts.tap.serviceimplementation;
 
import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.dao.OfferDao;
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.exception.OfferLetterServiceException;
import com.rts.tap.exception.OfferNotFoundException;
import com.rts.tap.feign.ApprovalLevelInterface;
import com.rts.tap.feign.CandidateInterface;
import com.rts.tap.feign.MRFInterface;
import com.rts.tap.feign.RecruiterDashBoardInterface;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.OfferService;
 
import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class OfferServiceImpl implements OfferService {
	OfferDao offerDao;
	OfferApprovalDao offerApprovalDao;
	ApprovalLevelInterface approverLevelDao;
	MRFInterface mrfDao;
	CandidateInterface candidateDao;
	RecruiterDashBoardInterface recruiterDashboardDao;
	
	public OfferServiceImpl(OfferDao offerDao, ApprovalLevelInterface approverLevelDao, OfferApprovalDao offerApprovalDao,
			CandidateInterface candidateDao, 	MRFInterface mrfDao, RecruiterDashBoardInterface recruiterDashboardDao) {
		super();
		this.offerDao = offerDao;
		this.approverLevelDao = approverLevelDao;
		this.offerApprovalDao = offerApprovalDao;
		this.candidateDao = candidateDao;
		this.mrfDao = mrfDao;
		this.recruiterDashboardDao = recruiterDashboardDao;
	}
 
	public Offer getOfferLetterByCandidateId(Long candidateId) {
	    if (candidateId == null) {
	        throw new IllegalArgumentException("Candidate ID must not be null");
	    }
	    Offer offer = offerDao.findOfferByCandidateId(candidateId);
	    if (offer == null) {
	        throw new OfferNotFoundException("Offer is not available for candidate ID: " + candidateId);
	    }
	    return offer;
	}
 
	@Override
	public Offer generateOfferLetter(OfferLetterDto offerLetterDto, MultipartFile offerLetter) 
	        throws IOException {
	    Offer offer = new Offer();
	    try {
	        offer.setMrf(mrfDao.getMRFById(offerLetterDto.getMrfId()));
	        offer.setCandidate(candidateDao.getCandidateById(offerLetterDto.getCandidateId()));
	        offer.setOfferPackage(offerLetterDto.getOfferPackage());
	        offer.setJoiningDate(offerLetterDto.getJoiningDate());
	        offer.setOfferLetter(offerLetter.getBytes());
	        
	        offerDao.saveOffer(offer);

	        offer.setCandidateStatus("Pending");
	        offer.setIsBuHeadApproved(false);
	        
	        OfferApproval offerApproval = new OfferApproval();
	        ApproverLevel approverLevel = approverLevelDao
	                .getApproverLevelByMrfIdAndLevel(offerLetterDto.getMrfId(), 1);
	        
	        offerApproval.setApproverLevel(approverLevel);
	        offerApproval.setOffer(offer);
	        offerApproval.setStatus(MessageConstants.SET_OFFER_APPROVAL_STATUS);
	        
	        offerApprovalDao.saveOfferApproval(offerApproval);

	    } catch (DataAccessException e) {
	        throw new OfferLetterServiceException("Database access error while generating the offer letter");
	    } catch (IOException e) {
	        throw new OfferLetterServiceException("Error processing the offer letter file");
	    } catch (Exception e) {
	        throw new OfferLetterServiceException("Unexpected error while generating the offer letter");
	    }
	    
	    return offer;
	}
 
	@Override
	public Offer updateOfferLetter(Long offerId, OfferLetterDto offerLetterDto, MultipartFile offerLetter) throws IOException {
	    Offer offer;
	    try {
	        // Retrieve the existing offer based on the offerId
	        offer = offerDao.findByOfferId(offerId);
	        
	        // Check if the offer exists
	        if (offer == null) {
	            throw new OfferNotFoundException("Offer with ID " + offerId + " not found.");
	        }

	        // Update offer details
	        offer.setOfferPackage(offerLetterDto.getOfferPackage());
	        offer.setJoiningDate(offerLetterDto.getJoiningDate());
	        offer.setOfferLetter(offerLetter.getBytes());

	        // Update the offer in the database
	        return offerDao.updateOfferLetter(offer);

	    } catch (DataAccessException e) {
	        throw new OfferLetterServiceException("Database access error while updating the offer letter");
	    } catch (IOException e) {
	        throw new OfferLetterServiceException("Error processing the offer letter file");
	    } catch (Exception e) {
	        throw new OfferLetterServiceException("Unexpected error while updating the offer letter");
	    }
	}
	
	@Override
	public List<Offer> getOfferLetterByRecruiterId(Long employeeId) {
	    List<Candidate> candidates = recruiterDashboardDao.getCandidateByEmployeeId(employeeId);
	    List<Offer> offers = offerDao.findOfferByRecruiterId(employeeId, candidates);
	    if (offers.isEmpty()) {
	        throw new OfferNotFoundException("No offers found for recruiter with ID: " + employeeId);
	    }
	    return offers;
	}
}