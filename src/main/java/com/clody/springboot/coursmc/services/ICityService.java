package com.clody.springboot.coursmc.services;

import java.util.List;

import com.clody.springboot.coursmc.models.City;

public interface ICityService {
	List<City> findCities(Integer cityId);
}
