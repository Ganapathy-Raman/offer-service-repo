package com.rts.tap.daoimplementation;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.model.OfferApproval;

import jakarta.persistence.EntityManager;

@Repository
public class OfferApprovalDaoImpl implements OfferApprovalDao {

	EntityManager entityManager;

	public OfferApprovalDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void saveOfferApproval(OfferApproval offerApproval) {
		entityManager.persist(offerApproval);
	}

	@Override
	public void updateOfferApprovalStatus(OfferApproval offerApproval) {
		entityManager.merge(offerApproval);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OfferApproval> findOfferApprovalByCandidateIdAndMrfId(Long candidateId, Long mrfId) {
		String hql = "select oa from OfferApproval oa where oa.offer.candidate.candidateId = :candidateId and oa.offer.mrf.mrfId = :mrfId";
		return entityManager.createQuery(hql).setParameter("candidateId", candidateId).setParameter("mrfId", mrfId)
				.getResultList();
	}

	@Override
	public List<OfferApproval> findOfferApprovalByApproverId(Long recruitingmanagerId) {
		String hql = "select oa from OfferApproval oa where oa.approverLevel.employee.employeeId = :recruitingmanagerId";
		return entityManager.createQuery(hql).setParameter("recruitingmanagerId", recruitingmanagerId).getResultList();
	}

	@Override
	public List<OfferApproval> findOfferApprovalByBUId(Long employeeId) {
		String hql = "select oa from OfferApproval oa where oa.approverLevel.employee.employeeId = :employeeId";
		return entityManager.createQuery(hql).setParameter("employeeId", employeeId).getResultList();
	}

	@Override
	public OfferApproval findOfferApprovalById(Long offerApprovalId) {
		return entityManager.find(OfferApproval.class, offerApprovalId);
	}

}
