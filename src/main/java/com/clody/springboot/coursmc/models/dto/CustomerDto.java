package com.clody.springboot.coursmc.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.clody.springboot.coursmc.models.Customer;

public class CustomerDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Customer'sname is Requiered!")
	@Length(min=5, max=120, message="the name must have 5 characters minimum and 120 characters maxi")
	private String name;
	@NotEmpty(message="The Email is Requiered!")
	@Email(message="Invalid email!")
	private String email;

	public CustomerDto() {

	}

	public CustomerDto(Customer customer) {
		id = customer.getId();
		name = customer.getName();
		email = customer.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
