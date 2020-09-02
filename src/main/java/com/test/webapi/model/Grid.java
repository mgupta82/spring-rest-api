package com.test.webapi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Grid{
	
	private int length;
	private int breadth;
	
	public Grid(){
		super();
	}
	
	public Grid(int length,int breadth){
		this.length = length;
		this.breadth = breadth;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getBreadth() {
		return breadth;
	}

	public void setBreadth(int breadth) {
		this.breadth = breadth;
	}
	
	public String toString(){
		return "length:" +length + " breadth:" + breadth;
	}	
}
