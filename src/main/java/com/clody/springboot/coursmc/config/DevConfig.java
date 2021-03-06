package com.clody.springboot.coursmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.clody.springboot.coursmc.mail.service.EmailService;
import com.clody.springboot.coursmc.mail.service.SmtpEmailService;
import com.clody.springboot.coursmc.services.impls.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBService dBService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}
		dBService.instantiateTestDatabease();
		return true;
	}
	
	@Bean
	public EmailService emailService() {	
		return new SmtpEmailService();
	}
}
