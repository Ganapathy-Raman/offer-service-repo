package com.rts.tap.service.impl.recruiter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.rts.tap.dao.OfferApprovalDao;
import com.rts.tap.dao.OfferDao;
import com.rts.tap.exception.OfferApprovalNotFoundException;
import com.rts.tap.feign.ApprovalLevelInterface;
import com.rts.tap.model.ApproverLevel;
import com.rts.tap.model.Offer;
import com.rts.tap.model.OfferApproval;
import com.rts.tap.serviceimplementation.OfferApprovalServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OfferApprovalServiceImplTest {

    @Mock
    private OfferApprovalDao offerApprovalDao;

    @Mock
    private OfferDao offerDao;

    @Mock
    private ApprovalLevelInterface approverLevelDao;

    @InjectMocks
    private OfferApprovalServiceImpl offerApprovalService;

    private OfferApproval offerApproval;
    private Offer offer;
    private ApproverLevel approverLevel;

    @BeforeEach
    void setUp() {
        offer = new Offer();
        offer.setOfferId(1L);
        offerApproval = new OfferApproval();
        offerApproval.setOffer(offer);
        approverLevel = new ApproverLevel();
        approverLevel.setApproverId(1L);
        approverLevel.setLevel(1);
        offerApproval.setApproverLevel(approverLevel);
        offerApproval.setStatus("approved");
    }

    @Test
    void testUpdateOfferApprovalStatus_DataAccessException() {
        doThrow(new DataAccessResourceFailureException("Database access error")).when(offerApprovalDao).updateOfferApprovalStatus(any(OfferApproval.class));
        
        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.updateOfferApprovalStatus(offerApproval);
        });
    }

    @Test
    void testGetOfferApprovalByCandidateIdAndMrfId_NotFound() {
        when(offerApprovalDao.findOfferApprovalByCandidateIdAndMrfId(anyLong(), anyLong())).thenReturn(new ArrayList<>());

        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.getOfferApprovalByCandidateIdAndMrfId(1L, 1L);
        });
    }

    @Test
    void testGetOfferApprovalByRecruitingManagerId_NotFound() {
        when(offerApprovalDao.findOfferApprovalByApproverId(anyLong())).thenReturn(new ArrayList<>());

        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.getOfferApprovalByRecruitingManagerId(1L);
        });
    }

    @Test
    void testGetOfferApprovalByBUId_NotFound() {
        when(offerApprovalDao.findOfferApprovalByBUId(anyLong())).thenReturn(new ArrayList<>());

        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.getOfferApprovalByBUId(1L);
        });
    }

    @Test
    void testUpdateOfferApprovalStatus_OtherException() {
        doThrow(new RuntimeException("Unexpected error")).when(offerApprovalDao).updateOfferApprovalStatus(any(OfferApproval.class));

        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.updateOfferApprovalStatus(offerApproval);
        });
    }

    @Test
    void testUpdateOfferApprovalStatusBU_Approved() {
        offerApproval.setStatus("approved");

        offerApprovalService.updateOfferApprovalStatusBU(offerApproval);

        verify(offerDao, times(1)).updateOfferLetter(any(Offer.class));
        verify(offerApprovalDao, times(1)).updateOfferApprovalStatus(any(OfferApproval.class));
    }

    @Test
    void testUpdateOfferApprovalStatusBU_Rejected() {
        offerApproval.setStatus("rejected");

        offerApprovalService.updateOfferApprovalStatusBU(offerApproval);

        verify(offerDao, times(1)).updateOfferLetter(any(Offer.class));
        verify(offerApprovalDao, times(1)).updateOfferApprovalStatus(any(OfferApproval.class));
    }

    @SuppressWarnings("serial")
	@Test
    void testUpdateOfferApprovalStatusBU_DataAccessException() {
        doThrow(new DataAccessException("Database access error") {}).when(offerDao).updateOfferLetter(any(Offer.class));

        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.updateOfferApprovalStatusBU(offerApproval);
        });
    }

    @Test
    void testUpdateOfferApprovalStatusBU_OtherException() {
        doThrow(new RuntimeException("Unexpected error")).when(offerApprovalDao).updateOfferApprovalStatus(any(OfferApproval.class));

        assertThrows(OfferApprovalNotFoundException.class, () -> {
            offerApprovalService.updateOfferApprovalStatusBU(offerApproval);
        });
    }
}