package com.clody.springboot.coursmc.models;

import javax.persistence.Entity;
import com.clody.springboot.coursmc.models.enums.StatusPayment;

@Entity
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;

	public PaymentWithCard() {

	}

	public PaymentWithCard(Integer id, StatusPayment status, Invoice invoice, Integer numberOfInstallments) {
		super(id, status, invoice);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

	
}
