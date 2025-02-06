package com.rts.tap.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Candidate;

@FeignClient(name = "candidate-service", path = "candidate-service")
public interface CandidateInterface {

	@GetMapping(APIConstants.BASE_CANDIDATE_URL + APIConstants.GET_BY_ID_CANDIDATE_URL)
	public Candidate getCandidateById(@PathVariable Long id);
}
