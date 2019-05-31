package com.clody.springboot.coursmc.services;

import java.util.List;

import com.clody.springboot.coursmc.models.Category;

public interface ICategoryService {
	Category findById(Integer id);
	Category insert(Category category);
	Category update(Category category);
	void delete(Integer id);
	List<Category> findAll();
}
