package com.sbc.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sbc.entity.Email;

/**
 * Calling ms1_email_service endpoint '/email/send'
 * FeignClient is another way of using RestTemplate.
 * It is also blocking as opposed to WebClient/WebFlux. 
 * @author suny4 02/21/21
 *
 */
@FeignClient(name="email-service")
public interface FeignClientEmailService {

	@RequestMapping(method=RequestMethod.POST, value="/email/send")
	public void sendEmail(@RequestBody Email email);
	
}
