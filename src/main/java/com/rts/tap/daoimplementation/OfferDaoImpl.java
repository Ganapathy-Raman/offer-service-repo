package com.rts.tap.daoimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.OfferDao;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.Offer;

import jakarta.persistence.EntityManager;

@Repository
public class OfferDaoImpl implements OfferDao {

	EntityManager entityManager;

	public OfferDaoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void saveOffer(Offer offer) {
		entityManager.persist(offer);
	}

	@Override
	public Offer findOfferByCandidateId(Long candidateId) {
		try {
			String hql = "Select o from Offer o where o.candidate.candidateId = :candidateId";
			return (Offer) entityManager.createQuery(hql).setParameter("candidateId", candidateId).getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Offer updateOfferLetter(Offer offer) {
		return entityManager.merge(offer);
	}

	@Override
	public Offer findByOfferId(Long offerId) {
		return entityManager.find(Offer.class, offerId);
	}

	@Override
	public List<Offer> findOfferByRecruiterId(Long employeeId, List<Candidate> candidates) {

		List<Long> candidateIds = candidates.stream().map(Candidate::getCandidateId).collect(Collectors.toList());

		if (candidateIds.isEmpty()) {
			return new ArrayList<>();
		}

		String hql = "Select o from Offer o where o.candidate.candidateId in :candidateIds";
		return entityManager.createQuery(hql, Offer.class).setParameter("candidateIds", candidateIds).getResultList();
	}
}