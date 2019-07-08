package com.clody.springboot.coursmc.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.models.dto.CustomerNewDto;

public interface ICustomerService {
	Customer findById(Integer id);
	Customer findByEmail(String email);
	Customer insert(Customer customer);
	Customer fromDto(CustomerDto customerDto);
	Customer fromNewDto(CustomerNewDto customerNewDto);
	Customer update(Customer customer);
	void delete(Integer id);
	List<Customer> findAll();
	Page<Customer> findPage(Integer page, Integer linesPerPage, String derection, String orderBy);
	void uploadProfilePiture(MultipartFile file, Integer id) throws IOException;
}
