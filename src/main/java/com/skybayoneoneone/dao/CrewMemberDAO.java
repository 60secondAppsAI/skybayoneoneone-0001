package com.skybayoneoneone.dao;

import java.util.List;

import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.domain.CrewMember;





public interface CrewMemberDAO extends GenericDAO<CrewMember, Integer> {
  
	List<CrewMember> findAll();
	






}


