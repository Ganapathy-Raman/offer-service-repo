package com.rts.tap.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.model.Offer;
import com.rts.tap.service.OfferService;

@RestController
@RequestMapping(path = APIConstants.BASE_URL)
public class OfferController {

    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping(path = APIConstants.GENERATE_OFFERLETTER_FOR_CANDIDATE, consumes = "multipart/form-data")
    public ResponseEntity<Offer> generateOfferLetter(@ModelAttribute OfferLetterDto offerLetterDto,
            @RequestParam("offerLetter") MultipartFile offerLetter) throws IOException {
        logger.info("Generating offer letter for candidate: {}", offerLetterDto.getCandidateId());
        Offer offer = offerService.generateOfferLetter(offerLetterDto, offerLetter);
        logger.info("Successfully generated offer letter with ID: {}", offer.getOfferId());
        return ResponseEntity.ok(offer);
    }

    @GetMapping(APIConstants.GET_OFFERLETTER_BY_CANDIDATE_ID)
    public ResponseEntity<Offer> getOfferLetter(@PathVariable Long candidateId) {
        logger.info("Fetching offer letter for candidate ID: {}", candidateId);
        Offer offer = offerService.getOfferLetterByCandidateId(candidateId);
        logger.info("Successfully fetched offer letter with ID: {}", offer.getOfferId());
        return ResponseEntity.ok(offer);
    }

    @PutMapping(path = APIConstants.UPDATE_OFFER_LETTER, consumes = "multipart/form-data")
    public ResponseEntity<Offer> updateOfferLetter(@PathVariable Long offerId,
            @ModelAttribute OfferLetterDto offerLetterDto, @RequestParam("offerLetter") MultipartFile offerLetter)
            throws IOException {
        logger.info("Updating offer letter with ID: {}", offerId);
        Offer updatedOffer = offerService.updateOfferLetter(offerId, offerLetterDto, offerLetter);
        logger.info("Successfully updated offer letter with ID: {}", updatedOffer.getOfferId());
        return ResponseEntity.ok(updatedOffer);
    }
    
    @GetMapping(APIConstants.GET_OFFERLETTER_BY_RECRUITER_ID)
    public ResponseEntity<List<Offer>> getOfferLetterByRecruiterId(@PathVariable Long employeeId) {
        logger.info("Fetching offer letters for recruiter ID: {}", employeeId);
        List<Offer> offers = offerService.getOfferLetterByRecruiterId(employeeId);
        logger.info("Successfully fetched {} offer letters for recruiter ID: {}", offers.size(), employeeId);
        return ResponseEntity.ok(offers);
    }
}