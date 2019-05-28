package com.clody.springboot.coursmc.services.impls;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.services.ICustomerService;
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

}
