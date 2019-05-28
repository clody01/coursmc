package com.clody.springboot.coursmc.services;

import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.Customer;

public interface ICustomerService {
	Customer findById(Integer id);
}
