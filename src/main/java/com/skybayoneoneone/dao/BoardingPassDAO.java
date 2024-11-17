package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.BoardingPass;





public interface BoardingPassDAO extends GenericDAO<BoardingPass, Integer> {
  
	List<BoardingPass> findAll();
	






}


