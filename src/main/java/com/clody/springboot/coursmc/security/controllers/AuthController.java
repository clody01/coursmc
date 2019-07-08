package com.clody.springboot.coursmc.security.controllers;

import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clody.springboot.coursmc.mail.dto.EmailDto;
import com.clody.springboot.coursmc.security.JWTUtil;
import com.clody.springboot.coursmc.security.UserSS;
import com.clody.springboot.coursmc.security.services.AuthService;
import com.clody.springboot.coursmc.security.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthService authService;
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws ParseException {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto emailDto) throws ParseException {
		authService.sendNewPassword(emailDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
