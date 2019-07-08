package com.clody.springboot.coursmc.security.filters;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.clody.springboot.coursmc.security.CredentialsDto;
import com.clody.springboot.coursmc.security.JWTUtil;
import com.clody.springboot.coursmc.security.UserSS;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	 public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
	    	    this.authenticationManager = authenticationManager;
	        this.jwtUtil = jwtUtil;
	    }
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredentialsDto creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDto.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>());
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chian,
			Authentication auth) throws IOException, ServletException {
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		try {
			String token = jwtUtil.generateToken(username);
			response.addHeader("Authorization", "Bearer "+ token);
			response.addHeader("access-control-expose-headers", "Authorization");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	};
}
