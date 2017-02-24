package com.deltek.trafficlive.messaging.security;

import org.granite.gravity.security.GravityDestinationSecurizer;
import org.granite.gravity.security.GravityInvocationContext;
import org.granite.messaging.service.security.SecurityServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomDestinationSecurizer implements GravityDestinationSecurizer {

	private final Logger log = LoggerFactory.getLogger(CustomDestinationSecurizer.class);
	
	@Override
	public void canSubscribe(GravityInvocationContext context) throws SecurityServiceException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("canSubscribe - Authentication: " +authentication);
	}

	@Override
	public void canPublish(GravityInvocationContext context) throws SecurityServiceException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("canPublish - Authentication: ", authentication);
	}

}
