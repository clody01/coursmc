package com.clody.springboot.coursmc.models;

import java.util.Date;

import javax.persistence.Entity;

import com.clody.springboot.coursmc.models.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class PaymentWithTicket extends Payment{	
	private static final long serialVersionUID = 1L; 
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;
	
	public PaymentWithTicket() {		 
	}
	
	public PaymentWithTicket(Integer id, StatusPayment status, Invoice invoice,Date dueDate, Date paymentDate) {
		super(id, status, invoice);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
