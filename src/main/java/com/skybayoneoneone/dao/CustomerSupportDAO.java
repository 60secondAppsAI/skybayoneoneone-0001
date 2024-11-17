package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.CustomerSupport;





public interface CustomerSupportDAO extends GenericDAO<CustomerSupport, Integer> {
  
	List<CustomerSupport> findAll();
	






}


