package com.clody.springboot.coursmc.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.dto.CategoryDto;

public interface ICategoryService {
	Category findById(Integer id);
	Category insert(Category category);
	Category fromDto(CategoryDto categoryDto);
	Category update(Category category);
	void delete(Integer id);
	List<Category> findAll();
	Page<Category> findPage(Integer page, Integer linesPerPage, String derection, String orderBy);
}
