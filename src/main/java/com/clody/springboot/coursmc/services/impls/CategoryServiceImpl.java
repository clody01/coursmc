package com.clody.springboot.coursmc.services.impls;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.ICategoryDao;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.services.ICategoryService;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public Category findById(Integer id) {
		Category category = categoryDao.findById(id).orElse(null);
		if (category == null) {
			throw new ObjectNotFoundException("Object with ID = "+ id.toString() + " Of Type "+ Category.class.getName()+ " does not exist in database!");
		}
		return category;
	}

	@Override
	@Transactional
	public Category insert(Category category) {	
		category.setId(null);
		return categoryDao.save(category);
	}

	@Override
	@Transactional
	public Category update(Category category) {
		findById(category.getId());
		return categoryDao.save(category);
	}

}
