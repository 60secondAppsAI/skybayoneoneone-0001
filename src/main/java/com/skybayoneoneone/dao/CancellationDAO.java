package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.Cancellation;





public interface CancellationDAO extends GenericDAO<Cancellation, Integer> {
  
	List<Cancellation> findAll();
	






}


