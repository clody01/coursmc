package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clody.springboot.coursmc.models.Payment;


@Repository
public interface IPaymentDao extends JpaRepository<Payment, Integer> {

}
