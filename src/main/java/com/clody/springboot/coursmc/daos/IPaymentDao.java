package com.clody.springboot.coursmc.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clody.springboot.coursmc.models.Payment;


public interface IPaymentDao extends JpaRepository<Payment, Integer> {

}
