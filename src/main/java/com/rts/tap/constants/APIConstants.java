package com.rts.tap.constants;

public class APIConstants {
	
	public static final String BASE_URL = "/offer-service/tap";
	public static final String BASE_SCHEDULE_EMAIL_URL = BASE_URL + "/schedule";
	public static final String SAVE_SCHEDULE_EMAIL_URL = "/post";


	public static final String GET_OFFERLETTER_BY_RECRUITER_ID = "/getOfferLetterByRecruiterId/{employeeId}"; // offer
	public static final String GET_OFFER_APPROVAL_BY_RECRUITINGMANAGERID = "/getAllOffersByRMId/{recruitingManagerId}"; // offer
	public static final String GENERATE_OFFERLETTER_FOR_CANDIDATE = "/generateOfferLetter"; // offer
	public static final String GET_OFFERLETTER_BY_CANDIDATE_ID = "/getOfferLetterByCandidateId/{candidateId}"; // offer
	public static final String UPDATE_OFFER_LETTER = "/updateOfferLetter/{offerId}"; // offer
	public static final String UPDATE_OFFER_APPROVAL_STATUS = "/updateOfferApprovalStatus"; // offer
	public static final String GET_OFFER_APPROVAL_BY_CANDIDATE_ID_AND_MRF_ID = "/getOfferApprovalByCandidateIdAndMrfId"; // offer
	public static final String GET_OFFER_APPROVAL_STATUS_BU = "BUofferApproval/employee/{employeeId}"; // offer
	public static final String UPDATE_OFFER_APPROVAL_STATUS_BU = "BUofferApproval/updateOfferApprovalStatus"; // offer

	// Interface needed Constants
	public static final String GET_APPROVAL_LEVEL_BY_MRFID_LEVEL = "/{mrfId}/{level}";
	public static final String BASE_CANDIDATE_URL = BASE_URL + "/candidates";
	public static final String GET_BY_ID_CANDIDATE_URL = "/get/{id}";
	public static final String GET_MRF = "/mrf/getMrf/{mrfId}";
	public static final String RECRUITER_DASHBOARD = BASE_URL + "/api";
	public static final String GET_CANDIDATE_BY_EMPLOYEEID = "/getCandidateByEmployeeId/{employeeId}";

}
