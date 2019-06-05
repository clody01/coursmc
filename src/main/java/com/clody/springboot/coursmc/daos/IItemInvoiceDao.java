package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.ItemInvoice;


public interface IItemInvoiceDao extends JpaRepository<ItemInvoice, Integer> {
}
