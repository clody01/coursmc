package com.clody.springboot.coursmc.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.ICustomerDao;
import com.clody.springboot.coursmc.models.Customer;
import com.clody.springboot.coursmc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ICustomerDao customerDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerDao.findByEmail(email);
		if (customer == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getProfiles());
	}

}
