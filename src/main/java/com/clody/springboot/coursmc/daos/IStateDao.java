package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.State;

public interface IStateDao extends JpaRepository<State, Integer> {

}
