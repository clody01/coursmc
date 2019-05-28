package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.Category;


public interface ICategoryDao extends JpaRepository<Category, Integer> {

}
