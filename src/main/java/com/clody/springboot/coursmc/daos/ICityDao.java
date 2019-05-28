package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.City;


public interface ICityDao extends JpaRepository<City, Integer> {

}
