package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.FlightCrew;





public interface FlightCrewDAO extends GenericDAO<FlightCrew, Integer> {
  
	List<FlightCrew> findAll();
	






}


