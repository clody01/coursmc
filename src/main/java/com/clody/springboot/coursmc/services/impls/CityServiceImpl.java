package com.clody.springboot.coursmc.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.ICityDao;
import com.clody.springboot.coursmc.models.City;
import com.clody.springboot.coursmc.services.ICityService;

@Service
public class CityServiceImpl implements ICityService {
	@Autowired
	private ICityDao cityDao;

	@Override
	public List<City> findCities(Integer cityId) {		 
		return cityDao.findCities(cityId);
	}	 
}
