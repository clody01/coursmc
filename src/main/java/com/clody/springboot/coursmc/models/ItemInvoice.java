package com.clody.springboot.coursmc.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore; 
@Entity
public class ItemInvoice  implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemInvoicePK id = new ItemInvoicePK();
	
	private Double discount;
	private Integer count;
	private Double price;
	
	public ItemInvoice() {}
	public ItemInvoice(Invoice invoice, Product product, Double discount, Integer count, Double price) {
		this.id.setProduct(product);
		this.id.setInvoice(invoice);
		this.discount = discount;
		this.count = count;
		this.price = price;
	}

	public double getSubTotal() {
		return (price - discount)* count;
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	@JsonIgnore
	public Invoice getInvoice() {
		return id.getInvoice();
	}
	public void setInvoice(Invoice invoice) {
		id.setInvoice(invoice);
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
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("fr", "FR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Count: ");
		builder.append(", price: ");
		builder.append(getCount());
		builder.append(", Unit Price: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	
 
}
