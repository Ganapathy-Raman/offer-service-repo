package com.rts.tap.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.Candidate;

@FeignClient(name="recruiter-service", path = "recruiter-service")
public interface RecruiterDashBoardInterface {
	
	@GetMapping("/tap/api"+APIConstants.GET_CANDIDATE_BY_EMPLOYEEID)
    public List<Candidate> getCandidateByEmployeeId(@PathVariable Long employeeId);

}
