package com.clody.springboot.coursmc.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.daos.IAddressDao;
import com.clody.springboot.coursmc.daos.ICityDao;
import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Address;
import com.clody.springboot.coursmc.models.City;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.models.dto.CustomerNewDto;
import com.clody.springboot.coursmc.models.enums.CustomerType;
import com.clody.springboot.coursmc.services.ICustomerService;
import com.clody.springboot.coursmc.services.excepetions.DataIntegrityException;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IAddressDao addressDao;

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Integer id) {
		Customer customer = customerDao.findById(id).orElse(null);
		if (customer == null) {
			throw new ObjectNotFoundException("Object with ID = " + id.toString() + " Of Type "
					+ Customer.class.getName() + " does not exist in database!");
		}
		return customer;
	}

	@Override
	@Transactional
	public Customer insert(Customer customer) {
		customer.setId(null);
		addressDao.saveAll(customer.getAddressList());
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
			throw new DataIntegrityException("This Customercan ot be delete because it has some Payments");
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
		return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getEmail(), null, null);
	}

	@Override
	public Customer fromNewDto(CustomerNewDto customerNewDto) {
		Customer customer = new Customer(null, customerNewDto.getName(), customerNewDto.getEmail(),
				CustomerType.toEnum(customerNewDto.getCustomerType()), customerNewDto.getCpfOuCnpj());
		City city = cityDao.findById(customerNewDto.getCityId()).orElse(null);
		Address address = new Address(null, customerNewDto.getPublicPlace(), customerNewDto.getNumber(),
				customerNewDto.getComplement(), customerNewDto.getNeighborhood(), customerNewDto.getZipCode(), customer,
				city);
		customer.getAddressList().add(address);
		customer.getTelephones().add(customerNewDto.getPhone1());
		if (customerNewDto.getPhone2() != null) {
			customer.getTelephones().add(customerNewDto.getPhone2());
		}
		if (customerNewDto.getPhone3() != null) {
			customer.getTelephones().add(customerNewDto.getPhone3());
		}
		return customer;
	}
}
