package com.clody.springboot.coursmc.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.daos.ICategoryDao;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.dto.CategoryDto;
import com.clody.springboot.coursmc.services.ICategoryService;
import com.clody.springboot.coursmc.services.excepetions.DataIntegrityException;
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
			throw new ObjectNotFoundException("Object with ID = " + id.toString() + " Of Type "
					+ Category.class.getName() + " does not exist in database!");
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
		Category newCategory = findById(category.getId());
		updateData(newCategory, category);
		return categoryDao.save(newCategory);
	}

	private void updateData(Category newCategory, Category category) {
		newCategory.setName(category.getName());
	}

	@Override
	public void delete(Integer id) {
		findById(id);
		try {
			categoryDao.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("This Category has some Products");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Category> findPage(Integer page, Integer linesPerPage, String derection, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(derection), orderBy);
		return categoryDao.findAll(pageRequest);
	}

	@Override
	public Category fromDto(CategoryDto categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getName());
	}
}
