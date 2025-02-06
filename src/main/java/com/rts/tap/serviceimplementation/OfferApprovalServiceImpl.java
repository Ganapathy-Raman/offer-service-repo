package com.rts.tap.serviceimplementation;

import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.dao.OfferDao;
import com.rts.tap.exception.OfferApprovalNotFoundException;
import com.rts.tap.feign.ApprovalLevelInterface;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Offer;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.OfferApprovalService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OfferApprovalServiceImpl implements OfferApprovalService {
	
	private OfferApprovalDao offerApprovalDao;
	private OfferDao offerDao;
	ApprovalLevelInterface approverLevelDao;
	 
	public OfferApprovalServiceImpl(OfferApprovalDao offerApprovalDao, ApprovalLevelInterface approverLevelDao,
			OfferDao offerDao) {
		super();
		this.offerApprovalDao = offerApprovalDao;
		this.approverLevelDao = approverLevelDao;
		this.offerDao = offerDao;
	}

	@Override
	public void updateOfferApprovalStatus(OfferApproval offerApproval) {
	    try {
	        offerApprovalDao.updateOfferApprovalStatus(offerApproval);

	        if (offerApproval.getStatus().equalsIgnoreCase("approved")) {
	            OfferApproval offerApproval2 = new OfferApproval();
	            ApproverLevel approverLevel = approverLevelDao.getApproverLevelByMrfIdAndLevel(
	                    offerApproval.getOffer().getMrf().getMrfId(), offerApproval.getApproverLevel().getLevel() + 1);
	            offerApproval2.setApproverLevel(approverLevel);
	            offerApproval2.setOffer(offerApproval.getOffer());
	            offerApproval2.setStatus(MessageConstants.SET_OFFER_APPROVAL_STATUS);
	            offerApprovalDao.saveOfferApproval(offerApproval2);
	        }
	    } catch (DataAccessException e) {
	        throw new OfferApprovalNotFoundException("Database access error while updating or saving offer approval");
	    } catch (OfferApprovalNotFoundException e) {
	        throw new OfferApprovalNotFoundException("Approver level with ID " + offerApproval.getApproverLevel().getApproverId() + " not found.");
	    } catch (Exception e) {
	        throw new OfferApprovalNotFoundException("Unexpected error while updating or saving offer approval");
	    }
	}

	@Override
	public List<OfferApproval> getOfferApprovalByCandidateIdAndMrfId(Long candidateId, Long mrfId) {
		List<OfferApproval> offerApprovals = offerApprovalDao.findOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId);
		if(offerApprovals.isEmpty()) {
			throw new OfferApprovalNotFoundException("Offer Approval not available for this Candidate Id: "+candidateId+" and Mrf Id: "+mrfId);
		}
		return offerApprovals;
	}
	
	@Override
	public List<OfferApproval> getOfferApprovalByRecruitingManagerId(Long recruitingManagerId) {
		List<OfferApproval> offerApprovals = offerApprovalDao.findOfferApprovalByApproverId(recruitingManagerId);
		if(offerApprovals.isEmpty()) {
			throw new OfferApprovalNotFoundException("No Offer Approvals for this Recruiting Manager Id: "+recruitingManagerId);
		}
		return offerApprovals;
	}

	@Override
		public List<OfferApproval> getOfferApprovalByBUId(Long employeeId) {
			List<OfferApproval> offerApprovals = offerApprovalDao.findOfferApprovalByBUId(employeeId);
			if(offerApprovals.isEmpty()) {
				throw new OfferApprovalNotFoundException("No Offer Approvals for this Employee Id: "+employeeId);
			}
			return offerApprovals;
		}
		
	@Override
	public void updateOfferApprovalStatusBU(OfferApproval offerApproval) {
	    try {
	        if ("approved".equalsIgnoreCase(offerApproval.getStatus())) {
	            Offer offer = offerApproval.getOffer();
	            offer.setIsBuHeadApproved(true);
	            Date currentDate = new Date(System.currentTimeMillis());
	            offer.setCreateDate(currentDate);
	            offerDao.updateOfferLetter(offer);
	        } else if ("rejected".equalsIgnoreCase(offerApproval.getStatus())) {
	            Offer offer = offerApproval.getOffer();
	            offerDao.updateOfferLetter(offer);
	        }

	        // Update the approval status in the database
	        offerApprovalDao.updateOfferApprovalStatus(offerApproval);

	    } catch (DataAccessException e) {
	        throw new OfferApprovalNotFoundException("Database access error while updating offer approval status");
	    } catch (Exception e) {
	        throw new OfferApprovalNotFoundException("Unexpected error while updating offer approval status");
	    }
	}
	 
	 	
}
