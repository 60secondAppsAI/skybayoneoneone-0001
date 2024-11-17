package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.Pilot;





public interface PilotDAO extends GenericDAO<Pilot, Integer> {
  
	List<Pilot> findAll();
	






}


