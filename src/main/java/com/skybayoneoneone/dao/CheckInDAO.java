package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.CheckIn;





public interface CheckInDAO extends GenericDAO<CheckIn, Integer> {
  
	List<CheckIn> findAll();
	






}


