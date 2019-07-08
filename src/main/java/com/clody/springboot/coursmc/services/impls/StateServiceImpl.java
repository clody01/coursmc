package com.clody.springboot.coursmc.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clody.springboot.coursmc.daos.IStateDao;
import com.clody.springboot.coursmc.models.State;
import com.clody.springboot.coursmc.services.IStateService;

@Service
public class StateServiceImpl implements IStateService {
	@Autowired
	private IStateDao stateDao;

	@Override
	public List<State> findAll() {
		return stateDao.findAllByOrderByName();
	}
}
