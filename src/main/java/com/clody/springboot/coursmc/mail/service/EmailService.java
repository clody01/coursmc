package com.clody.springboot.coursmc.mail.service;

import org.springframework.mail.SimpleMailMessage;

import com.clody.springboot.coursmc.models.Invoice;

public interface EmailService {
	void sendOrderConfirmationEmail(Invoice invoice);
	void sendEmail(SimpleMailMessage msg);
}
