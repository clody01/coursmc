package com.clody.springboot.coursmc.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.clody.springboot.coursmc.models.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private Integer customerType;
	private String cpfOuCnpj;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> addressList = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "telephones")
	private Set<String> telephones = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices = new ArrayList<>();

	public Customer() {
	}

	public Customer(Integer id, String name, String email, CustomerType customerType, String cpfOuCnpj) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.customerType = (customerType == null) ? null : customerType.getCode();
		this.cpfOuCnpj = cpfOuCnpj;
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

	public CustomerType getCustomerType() {
		return CustomerType.toEnum(customerType);
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType.getCode();
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Set<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<String> telephones) {
		this.telephones = telephones;
	}

	/*
	 * public List<Invoice> getInvoices() { return invoices; } public void
	 * setInvoices(List<Invoice> invoices) { this.invoices = invoices; }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
