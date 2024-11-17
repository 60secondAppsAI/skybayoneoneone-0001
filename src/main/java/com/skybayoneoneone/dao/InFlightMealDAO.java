package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.InFlightMeal;





public interface InFlightMealDAO extends GenericDAO<InFlightMeal, Integer> {
  
	List<InFlightMeal> findAll();
	






}


