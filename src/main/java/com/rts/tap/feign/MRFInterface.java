package com.rts.tap.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.model.MRF;

@FeignClient(name = "mrf-service")
public interface MRFInterface {

	@GetMapping(APIConstants.BASE_URL + APIConstants.GET_MRF)
	public MRF getMRFById(@PathVariable Long mrfId);
}
