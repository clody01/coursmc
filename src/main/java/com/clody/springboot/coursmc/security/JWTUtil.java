package com.clody.springboot.coursmc.security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private String expiration;
	public String generateToken(String username) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(simpleDateFormat.parse(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512,secret.getBytes())
				.compact();
	}
	
}
