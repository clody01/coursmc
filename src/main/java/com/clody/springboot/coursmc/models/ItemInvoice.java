package com.clody.springboot.coursmc.models;

import java.io.Serializable; 
import java.util.Date; 

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne; 
@Entity
public class ItemInvoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemInvoicePK id = new ItemInvoicePK();
	
	private Double discount;
	private Integer count;
	private Double price;
	public ItemInvoice() {}
	public ItemInvoice(Invoice invoice, Product product, Double discount, Integer count, Double price) {
		super();
		id.setProduct(product);
		id.setInvoice(invoice);
		this.discount = discount;
		this.count = count;
		this.price = price;
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	public Invoice getInvoice() {
		return id.getInvoice();
	}
	public ItemInvoicePK getId() {
		return id;
	}
	
	public void setId(ItemInvoicePK id) {
		this.id = id;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
		ItemInvoice other = (ItemInvoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
 
}
