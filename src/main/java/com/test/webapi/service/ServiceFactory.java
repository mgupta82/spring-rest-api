package com.test.webapi.service;

import java.util.HashMap;


public class ServiceFactory {
	private static HashMap<Class, Service> serviceMapping = new HashMap<>();
	
	public Service getService(Class serviceClass){
		
		Service service = (Service) serviceMapping.get(serviceClass);
		
		if(service == null){
			try{
				service = (Service) Class.forName(serviceClass.getName()+"Impl").newInstance();
				serviceMapping.put(serviceClass, service);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return service;
	}	
}
