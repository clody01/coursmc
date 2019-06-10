package com.clody.springboot.coursmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.clody.springboot.coursmc.services.impls.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dBService;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dBService.instantiateTestDatabease();
		return true;
	}
}