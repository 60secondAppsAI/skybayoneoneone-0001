package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


