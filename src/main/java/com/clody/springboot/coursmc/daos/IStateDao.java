package com.clody.springboot.coursmc.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.clody.springboot.coursmc.models.State;

public interface IStateDao extends JpaRepository<State, Integer> {
	
	@Transactional(readOnly=true)
	List<State> findAllByOrderByName();
}
