package com.clody.springboot.coursmc.mail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.Invoice;

public class AbstractEmailService implements EmailService {

	@Value("default.sender")
	private String sender;
	
	
	@Override
	public void sendOrderConfirmationEmail(Invoice invoice) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromInvoice(invoice);
		sendEmail(simpleMailMessage);
	}

	
	protected SimpleMailMessage prepareSimpleMailMessageFromInvoice(Invoice invoice) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(invoice.getCustomer().getEmail());
		simpleMailMessage.setFrom("mamadoumady69@gmail.com");	
		simpleMailMessage.setSubject("Invoice confirmation code: " + invoice.getId());
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(invoice.toString());
		return simpleMailMessage;
	}


	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendNewPasswordEmail(Customer customer, String newPassword) {
		SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(customer,newPassword);
		sendEmail(simpleMailMessage);
	}


	protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPassword) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(customer.getEmail());
		simpleMailMessage.setFrom("mamadoumady69@gmail.com");	
		simpleMailMessage.setSubject("Creating new password");
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText("Your new password: "+newPassword);
		return simpleMailMessage;
	}
}
