package com.clody.springboot.coursmc.daos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.Invoice;


public interface IInvoiceDao extends JpaRepository<Invoice, Integer> {
	@Transactional(readOnly = true)
	Page<Invoice> findByCustomer(Customer customer,Pageable pageRequest);
}
