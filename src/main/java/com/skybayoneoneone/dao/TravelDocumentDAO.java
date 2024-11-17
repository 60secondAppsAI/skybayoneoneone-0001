package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.TravelDocument;





public interface TravelDocumentDAO extends GenericDAO<TravelDocument, Integer> {
  
	List<TravelDocument> findAll();
	






}


