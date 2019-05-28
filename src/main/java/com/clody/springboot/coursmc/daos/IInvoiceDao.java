package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.Invoice;


public interface IInvoiceDao extends JpaRepository<Invoice, Integer> {
}
