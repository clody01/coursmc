package com.clody.springboot.coursmc.services;

import java.util.List;

import com.clody.springboot.coursmc.models.State;

public interface IStateService {
	List<State> findAll();
}
