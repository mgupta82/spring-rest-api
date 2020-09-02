package com.test.webapi.dao;

import java.util.HashMap;

public class DaoFactory {

	private static HashMap<Class, DynamoDao> daoMapping = new HashMap<>();
	
	public <T> T getDynamoDao(Class<T> daoClass){
		
		DynamoDao dao = (DynamoDao) daoMapping.get(daoClass);
		
		if(dao == null){
			try{
				dao = (DynamoDao) Class.forName(daoClass.getName().replace("Dao", "DynamoDao")).newInstance();
				daoMapping.put(daoClass, dao);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return (T)dao;
	}	
	
}
