package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clody.springboot.coursmc.models.Address;


public interface IAddressDao extends JpaRepository<Address, Integer> {

}
