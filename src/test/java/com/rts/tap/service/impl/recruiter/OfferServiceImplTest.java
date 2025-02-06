package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.dao.OfferDao;
import com.rts.tap.dto.OfferLetterDto;
import com.rts.tap.exception.OfferNotFoundException;
import com.rts.tap.feign.ApprovalLevelInterface;
import com.rts.tap.feign.CandidateInterface;
import com.rts.tap.feign.MRFInterface;
import com.rts.tap.feign.RecruiterDashBoardInterface;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Candidate;
import com.rts.tap.model.MRF;
import com.rts.tap.model.Offer;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.serviceimplementation.OfferServiceImpl;

class OfferServiceImplTest {

    @Mock
    private OfferDao offerDao;

    @Mock
    private OfferApprovalDao offerApprovalDao;

    @Mock
    private ApprovalLevelInterface approverLevelDao;

    @Mock
    private MRFInterface mrfDao;

    @Mock
    private CandidateInterface candidateDao;

    @Mock
    private RecruiterDashBoardInterface recruiterDashboardDao;

    @InjectMocks
    private OfferServiceImpl offerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("unused")
	@Test
    void testGenerateOfferLetter() throws IOException {
        OfferLetterDto offerLetterDto = new OfferLetterDto();
        offerLetterDto.setMrfId(1L);
        offerLetterDto.setCandidateId(1L);
        offerLetterDto.setOfferPackage("Package A");
        offerLetterDto.setJoiningDate("2023-10-01");

        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn("Mocked file content".getBytes());
        
        Candidate candidate = new Candidate();
        when(candidateDao.getCandidateById(1L)).thenReturn(candidate);
        when(mrfDao.getMRFById(1L)).thenReturn(new MRF()); 

        when(approverLevelDao.getApproverLevelByMrfIdAndLevel(1L, 1)).thenReturn(new ApproverLevel());

        Offer offer = offerService.generateOfferLetter(offerLetterDto, mockFile);

        verify(offerDao).saveOffer(any(Offer.class)); 
        verify(offerApprovalDao).saveOfferApproval(any(OfferApproval.class));
    }

    @Test
    void testGetOfferLetterByCandidateId() {
        Long candidateId = 1L;
        Offer mockOffer = new Offer();
        when(offerDao.findOfferByCandidateId(candidateId)).thenReturn(mockOffer);

        Offer result = offerService.getOfferLetterByCandidateId(candidateId);
        assertEquals(mockOffer, result);

        verify(offerDao).findOfferByCandidateId(candidateId);
    }

    @Test
    void testGetOfferLetterByCandidateIdNotFound() {
        Long candidateId = 2L;

        when(offerDao.findOfferByCandidateId(candidateId)).thenReturn(null);

        assertThrows(OfferNotFoundException.class, () -> {
            offerService.getOfferLetterByCandidateId(candidateId);
        });
    }

    @Test
    void testGetOfferLetterByRecruiterId() {
        Long employeeId = 1L;
        Candidate candidate = new Candidate();
        List<Candidate> candidates = Collections.singletonList(candidate);
        Offer mockOffer = new Offer();
        List<Offer> mockOffers = Collections.singletonList(mockOffer);
        
        when(recruiterDashboardDao.getCandidateByEmployeeId(employeeId)).thenReturn(candidates);
        when(offerDao.findOfferByRecruiterId(employeeId, candidates)).thenReturn(mockOffers);

        List<Offer> results = offerService.getOfferLetterByRecruiterId(employeeId);

        assertEquals(1, results.size());
        assertEquals(mockOffer, results.get(0));

        verify(recruiterDashboardDao).getCandidateByEmployeeId(employeeId);
        verify(offerDao).findOfferByRecruiterId(employeeId, candidates);
    }

}