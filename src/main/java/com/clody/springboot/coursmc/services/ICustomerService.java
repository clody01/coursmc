package com.clody.springboot.coursmc.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;

public interface ICustomerService {
	Customer findById(Integer id);
	Customer insert(Customer customer);
	Customer fromDto(CustomerDto customerDto);
	Customer update(Customer customer);
	void delete(Integer id);
	List<Customer> findAll();
	Page<Customer> findPage(Integer page, Integer linesPerPage, String derection, String orderBy);
}
