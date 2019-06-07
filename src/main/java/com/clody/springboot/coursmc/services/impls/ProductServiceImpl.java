package com.clody.springboot.coursmc.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.daos.ICategoryDao;
import com.clody.springboot.coursmc.daos.IProductDao;
import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.Product;
import com.clody.springboot.coursmc.services.IProductService;
import com.clody.springboot.coursmc.services.excepetions.ObjectNotFoundException;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public Product findById(Integer id) {
		Product product = productDao.findById(id).orElse(null);
		if (product == null) {
			throw new ObjectNotFoundException("Object with ID = "+ id.toString() + " Of Type "+ Product.class.getName()+ " does not exist in database!");
		}
		return product;
	}

	@Override
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String derection,
			String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(derection), orderBy);
		List<Category> categories = categoryDao.findAllById(ids);
		//return productDao.findDistinctByNameContainingAndCategoriesIn(name,categories,pageRequest);
		return productDao.search(name,categories,pageRequest);
	}
	
	 
}
