package com.clody.springboot.coursmc.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.clody.springboot.coursmc.services.validations.CustomerInsert;

@CustomerInsert
public class CustomerNewDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Customer'sname is Requiered!")
	@Length(min=5, max=120, message="the name must have 5 characters minimum and 120 characters maxi")
	private String name;
	
	@NotEmpty(message="The Email is Requiered!")
	@Email(message="Invalid email!")
	private String email;
	
	
	private Integer customerType;
	@NotEmpty(message="The cpfOuCnpj is Requiered!")
	private String cpfOuCnpj;

	private String publicPlace;
	@NotEmpty(message="The number is Requiered!")
	private String number;
	private String complement;
	private String neighborhood;
	@NotEmpty(message="The ZipCode is Requiered!")
	private String zipCode;
	@NotEmpty(message="The Phone number is Requiered!")
	private String phone1;
	private String phone2;
	private String phone3;
	private Integer cityId;
	
	public CustomerNewDto() {
		
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

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
