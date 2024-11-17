package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


