package com.rts.tap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.service.OfferApprovalService;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
public class OfferApprovalController {

    private final OfferApprovalService offerApprovalService;
    private static final Logger logger = LoggerFactory.getLogger(OfferApprovalController.class);

    public OfferApprovalController(OfferApprovalService offerApprovalService) {
        this.offerApprovalService = offerApprovalService;
    }

    @PutMapping(APIConstants.UPDATE_OFFER_APPROVAL_STATUS)
    public ResponseEntity<String> updateOfferApprovalStatus(@RequestBody OfferApproval offerApproval) {
        logger.info("Updating offer approval status for OfferApproval: {}", offerApproval);
        offerApprovalService.updateOfferApprovalStatus(offerApproval);
        logger.info("Successfully updated offer approval status.");
        return ResponseEntity.ok(MessageConstants.OFFER_APPROVAL_STATUS_UPDATED);
    }

    @GetMapping(APIConstants.GET_OFFER_APPROVAL_BY_CANDIDATE_ID_AND_MRF_ID)
    public ResponseEntity<List<OfferApproval>> getOfferApprovalByCandidateId(
            @RequestParam("candidateId") Long candidateId,
            @RequestParam("mrfId") Long mrfId) {
        logger.info("Fetching offer approvals for candidateId: {} and mrfId: {}", candidateId, mrfId);
        List<OfferApproval> approvals = offerApprovalService.getOfferApprovalByCandidateIdAndMrfId(candidateId, mrfId);
        logger.info("Retrieved {} offer approvals for candidateId: {} and mrfId: {}", approvals.size(), candidateId, mrfId);
        return ResponseEntity.ok(approvals);
    }

    @GetMapping(APIConstants.GET_OFFER_APPROVAL_BY_RECRUITINGMANAGERID)
    public ResponseEntity<List<OfferApproval>> getOfferApprovalByRMId(@PathVariable Long recruitingManagerId) {
        logger.info("Fetching offer approvals for recruitingManagerId: {}", recruitingManagerId);
        List<OfferApproval> approvals = offerApprovalService.getOfferApprovalByRecruitingManagerId(recruitingManagerId);
        logger.info("Retrieved {} offer approvals for recruitingManagerId: {}", approvals.size(), recruitingManagerId);
        return ResponseEntity.ok(approvals);
    }

    // Team A
    @GetMapping(APIConstants.GET_OFFER_APPROVAL_STATUS_BU)
    public ResponseEntity<List<OfferApproval>> getOfferApprovalByBU(@PathVariable Long employeeId) {
        logger.info("Fetching offer approvals for employeeId (BU): {}", employeeId);
        List<OfferApproval> approvals = offerApprovalService.getOfferApprovalByBUId(employeeId);
        logger.info("Retrieved {} offer approvals for employeeId (BU): {}", approvals.size(), employeeId);
        return ResponseEntity.ok(approvals);
    }

    @PutMapping(APIConstants.UPDATE_OFFER_APPROVAL_STATUS_BU)
    public ResponseEntity<String> updateOfferApprovalStatusBU(@RequestBody OfferApproval offerApproval) {
        logger.info("Updating BU offer approval status for OfferApproval: {}", offerApproval);
        offerApprovalService.updateOfferApprovalStatusBU(offerApproval);
        logger.info("Successfully updated BU offer approval status.");
        return ResponseEntity.ok(MessageConstants.OFFER_APPROVAL_STATUS_UPDATED);
    }
}