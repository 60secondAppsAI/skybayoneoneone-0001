package com.skybayoneoneone.service;

import com.skybayoneoneone.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}