package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.BaggageClaim;





public interface BaggageClaimDAO extends GenericDAO<BaggageClaim, Integer> {
  
	List<BaggageClaim> findAll();
	






}


