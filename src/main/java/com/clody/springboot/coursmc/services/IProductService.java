package com.clody.springboot.coursmc.services;

import com.clody.springboot.coursmc.models.Product;

public interface IProductService {
	Product findById(Integer id);
}
