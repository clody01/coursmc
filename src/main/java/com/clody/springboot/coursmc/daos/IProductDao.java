package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.Product;

public interface IProductDao extends JpaRepository<Product, Integer> {

}
