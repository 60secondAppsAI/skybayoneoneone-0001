package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.DelayNotice;





public interface DelayNoticeDAO extends GenericDAO<DelayNotice, Integer> {
  
	List<DelayNotice> findAll();
	






}


