package com.clody.springboot.coursmc.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.clody.springboot.coursmc.models.enums.StatusPayment;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer status;
	@OneToOne
	@JoinColumn(name = "id")
	@MapsId // To set the same invoiceId to Payment(payment_id)
	private Invoice invoice;

	public Payment() {
	}

	public Payment(Integer id, StatusPayment statusPayment, Invoice invoice) {
		this.id = id;
		this.status = statusPayment.getCode();
		this.invoice = invoice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusPayment getStatus() {
		return StatusPayment.toEnum(status) ;
	}

	public void setStatus(StatusPayment status) {
		this.status = status.getCode();
	}

	public Invoice getOrder() {
		return  invoice;
	}

	public void setOrder(Invoice invoice) {
		this.invoice = invoice;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
