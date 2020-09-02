package com.test.webapi.service;

import com.test.webapi.dao.DaoFactory;

public class Service {
	static final DaoFactory daoFactory = new DaoFactory();
	
	public <T> T getDao(Class<T> daoClass){
		return (T)daoFactory.getDynamoDao(daoClass);
	}	

}
