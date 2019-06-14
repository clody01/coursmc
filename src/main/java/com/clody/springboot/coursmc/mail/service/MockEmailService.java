package com.clody.springboot.coursmc.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Sending Email Emition...");
		LOG.info(msg.toString());
		LOG.info("Email sent.");
	}
}
