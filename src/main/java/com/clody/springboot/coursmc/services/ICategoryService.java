package com.clody.springboot.coursmc.services;

import com.clody.springboot.coursmc.models.Category;

public interface ICategoryService {
	Category findById(Integer id);
}
