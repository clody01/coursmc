package com.clody.springboot.coursmc.daos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.models.Category;
import com.clody.springboot.coursmc.models.Product;

public interface IProductDao extends JpaRepository<Product, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT p FROM Product p INNER JOIN p.categories cat WHERE p.name LIKE %:name% AND cat IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories,
			Pageable pageRequest);

	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories,
			Pageable pageRequest);

}
