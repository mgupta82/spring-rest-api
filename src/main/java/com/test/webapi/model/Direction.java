package com.test.webapi.model;

public enum Direction {
	NORTH("NORTH"),
	SOUTH("SOUTH"),
	EAST("EAST"),
	WEST("WEST");
	
	private String name;
	
	Direction(String name){
		this.name = name;
	}
	public String toString() 
	{ 
		return this.name; 
	}

}
