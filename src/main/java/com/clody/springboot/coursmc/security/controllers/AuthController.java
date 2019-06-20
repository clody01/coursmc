package com.clody.springboot.coursmc.security.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clody.springboot.coursmc.security.JWTUtil;
import com.clody.springboot.coursmc.security.UserSS;
import com.clody.springboot.coursmc.security.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private JWTUtil jwtUtil;
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws ParseException {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
