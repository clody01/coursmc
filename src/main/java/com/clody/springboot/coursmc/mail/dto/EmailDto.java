package com.clody.springboot.coursmc.mail.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="The Email is Requiered!")
	@Email(message="Invalid email!")
	private String email;

	public EmailDto() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
