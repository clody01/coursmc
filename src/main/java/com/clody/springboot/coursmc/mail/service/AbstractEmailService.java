package com.clody.springboot.coursmc.mail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.clody.springboot.coursmc.models.Invoice;

public class AbstractEmailService implements EmailService {

	@Value("default.sender")
	private String sender;
	
	
	@Override
	public void sendOrderConfirmationEmail(Invoice invoice) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromInvoice(invoice);
		sendEmail(simpleMailMessage);
	}

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub

	}

	protected SimpleMailMessage prepareSimpleMailMessageFromInvoice(Invoice invoice) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(invoice.getCustomer().getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Invoice confirmation code: " + invoice.getId());
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(invoice.toString());
		return simpleMailMessage;
	}
}
