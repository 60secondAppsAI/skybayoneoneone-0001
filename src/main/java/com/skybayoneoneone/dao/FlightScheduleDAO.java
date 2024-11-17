package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.FlightSchedule;





public interface FlightScheduleDAO extends GenericDAO<FlightSchedule, Integer> {
  
	List<FlightSchedule> findAll();
	






}


