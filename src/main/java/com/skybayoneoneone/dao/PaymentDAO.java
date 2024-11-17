package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


