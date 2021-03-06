package com.clody.springboot.coursmc.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat; 
@Entity
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;
	
	 
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "invoice")
	private Payment payment;
	
	 
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;
	
	@OneToMany(mappedBy="id.invoice")
	private Set<ItemInvoice> itemInvoices = new HashSet<>();

	public Invoice() {}

	public Invoice(Integer id, Date instant, Customer customer, Address deliveryAddress) {
		this.id = id;
		this.instant = instant;
		this.customer = customer;
		this.deliveryAddress = deliveryAddress;
	}

	public double getTotalValue() {
		double sum = 0.0;
		for (ItemInvoice itemInvoice : itemInvoices) {
			sum = sum + itemInvoice.getSubTotal();
		}
		return sum;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public Set<ItemInvoice> getItemInvoices() {
		return itemInvoices;
	}

	public void setItemInvoices(Set<ItemInvoice> itemInvoices) {
		this.itemInvoices = itemInvoices;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("fr", "FR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice number: ");
		builder.append(id);
		builder.append(", Instance: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Customer: ");
		builder.append(getCustomer().getName());
		builder.append(", Payment status: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetails: \n:");
		for (ItemInvoice itemInvoice : itemInvoices) {
			builder.append(itemInvoice.toString());
		}
		builder.append("Total: ");
		builder.append(nf.format(getTotalValue()));
		return builder.toString();
	}

}
