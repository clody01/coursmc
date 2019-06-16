package com.clody.springboot.coursmc.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


public class SmtpEmailService extends AbstractEmailService {
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		System.out.println("message: "+ msg);
		LOG.info("Sending Email...");
	///	System.out.println("mailSender: "+ mailSender);
		mailSender.send(msg);
		LOG.info("Email sent.");
	}
}
