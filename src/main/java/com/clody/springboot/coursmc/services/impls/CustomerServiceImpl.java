package com.clody.springboot.coursmc.services.impls;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.clody.springboot.coursmc.daos.IAddressDao;
import com.clody.springboot.coursmc.daos.ICityDao;
import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Address;
import com.clody.springboot.coursmc.models.City;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.models.dto.CustomerDto;
import com.clody.springboot.coursmc.models.dto.CustomerNewDto;
import com.clody.springboot.coursmc.models.enums.CustomerType;
import com.clody.springboot.coursmc.models.enums.Profile;
import com.clody.springboot.coursmc.security.UserSS;
import com.clody.springboot.coursmc.security.exceptions.AuthorizationException;
import com.clody.springboot.coursmc.security.services.UserService;
import com.clody.springboot.coursmc.services.ICustomerService;
import com.clody.springboot.coursmc.services.excepetions.DataIntegrityException;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;
import com.clody.springboot.coursmc.uploadandload.services.IUploadFileService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IAddressDao addressDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private IUploadFileService uploadFileService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Integer id) {
		// Security
		UserSS userSS = UserService.authenticated();
		if (userSS == null || !userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("access denied");
		}

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
		return new Customer(customerDto.getId(), customerDto.getName(), customerDto.getEmail(), null, null, null);
	}

	@Override
	public Customer fromNewDto(CustomerNewDto customerNewDto) {
		Customer customer = new Customer(null, customerNewDto.getName(), customerNewDto.getEmail(),
				CustomerType.toEnum(customerNewDto.getCustomerType()), customerNewDto.getCpfOuCnpj(),
				bCryptPasswordEncoder.encode(customerNewDto.getPassword()));
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

	// @Override
	public Customer uploadProfilePiturePlus(MultipartFile multiPartFile, Integer id) throws IOException {
		Customer customer = findById(id);
		String fileName = uploadFileService.copyFile(multiPartFile);
		// customer.setImageUrl(fileName);
		return customerDao.save(customer);
	}

	@Override
	public void uploadProfilePiture(MultipartFile multiPartFile, Integer id) throws IOException {
		Customer customer = findById(id);
		BufferedImage jpgImage = uploadFileService.getJpgImageFromFile(multiPartFile);
		jpgImage = uploadFileService.cropSquare(jpgImage);
		jpgImage = uploadFileService.resize(jpgImage,size);
		String fileName = prefix + customer.getId() + ".jpg";
		uploadFileService.copyFilePlus(jpgImage, fileName, "jpg");	
		
	}
}
