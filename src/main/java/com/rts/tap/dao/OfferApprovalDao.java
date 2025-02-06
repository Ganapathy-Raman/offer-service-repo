package com.rts.tap.dao;

import java.util.List;

import com.rts.tap.model.OfferApproval;

public interface OfferApprovalDao {

	void saveOfferApproval(OfferApproval offerApproval);

	void updateOfferApprovalStatus(OfferApproval offerApproval);

	List<OfferApproval> findOfferApprovalByCandidateIdAndMrfId(Long candidateId, Long mrfId);
	
	public List<OfferApproval> findOfferApprovalByApproverId(Long recruitingmanagerId) ;

	List<OfferApproval> findOfferApprovalByBUId(Long employeeId);
	OfferApproval findOfferApprovalById(Long offerApprovalId);

}
