package com.clody.springboot.coursmc.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.models.City;

public interface ICityDao extends JpaRepository<City, Integer> {
	
	@Transactional(readOnly=true)
	@Query("SELECT city FROM City city WHERE city.state.id = :stateId ORDER BY city.name")
	List<City> findCities(@Param("stateId") Integer stateId);
}
