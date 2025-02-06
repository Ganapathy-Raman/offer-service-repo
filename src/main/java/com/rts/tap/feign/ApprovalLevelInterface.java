package com.rts.tap.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.ApproverLevel;

@FeignClient(name = "mrf-service")
public interface ApprovalLevelInterface {

	@GetMapping(APIConstants.BASE_URL + APIConstants.GET_APPROVAL_LEVEL_BY_MRFID_LEVEL)
	public ApproverLevel getApproverLevelByMrfIdAndLevel(@PathVariable Long mrfId, @PathVariable int level);

}
