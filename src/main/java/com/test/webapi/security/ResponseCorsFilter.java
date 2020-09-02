package com.test.webapi.security;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseCorsFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		//headers.add("Access-Control-Allow-Origin", "http://xxx"); //allows CORS requests only coming from xxx		
		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");			
		headers.add("Access-Control-Allow-Headers", "origin, accept, Content-Type, X-Custom");
	}

}
