package com.clody.springboot.coursmc.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.clody.springboot.coursmc.models.Product;

public interface IProductService {
	Product findById(Integer id);
	Page<Product> search(String name, List<Integer> ids,Integer page, Integer linesPerPage, String derection, String orderBy);

}
