package com.clody.springboot.coursmc.services.impls;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.services.ICustomerService;
import com.clody.springboot.coursmc.services.excepetions.DataIntegrityException;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Integer id) {
		Customer customer = customerDao.findById(id).orElse(null);
		if (customer == null) {
			throw new ObjectNotFoundException("Object with ID = "+ id.toString() + " Of Type "+ Customer.class.getName()+ " does not exist in database!");
		}
		return customer;
	}

	@Override
	@Transactional
	public Customer insert(Customer customer) {
		customer.setId(null);
		return customerDao.save(customer);
	}

	@Override
	@Transactional
	public Customer update(Customer customer) {
		Customer newCustomer = findById(customer.getId());
		updateData(newCustomer, customer);
		return customerDao.save(newCustomer);
	}

	private void updateData(Customer newCustomer, Customer customer) {
		newCustomer.setName(customer.getName());
		newCustomer.setEmail(customer.getEmail());
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		try {
			customerDao.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("This Customer has some Products");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Customer> findPage(Integer page, Integer linesPerpage, String derection, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerpage, Direction.valueOf(derection), orderBy);
		return customerDao.findAll(pageRequest);
	}

	@Override
	public Customer fromDto(CustomerDto customerDto) {
		return new Customer(customerDto.getId(), customerDto.getName(),customerDto.getEmail(), null, null);
	}

}
