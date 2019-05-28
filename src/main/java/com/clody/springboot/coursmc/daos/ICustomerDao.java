package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.Customer;


public interface ICustomerDao extends JpaRepository<Customer, Integer> {

}
