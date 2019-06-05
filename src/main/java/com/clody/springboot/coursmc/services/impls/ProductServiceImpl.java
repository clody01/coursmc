package com.clody.springboot.coursmc.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.daos.IProductDao;
import com.clody.springboot.coursmc.models.Product;
import com.clody.springboot.coursmc.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public Product findById(Integer id) {
		return productDao.findById(id).orElse(null);
	}

}
