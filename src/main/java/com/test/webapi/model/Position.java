package com.test.webapi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;

@DynamoDBDocument
public class Position {	
	
	int xpos;
	int ypos;
	Direction angle;
	
	public Position(){
		super();
	}
	
	public Position(int xpos,int ypos,Direction angle){
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
	}
	
	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	@DynamoDBMarshalling(marshallerClass = DirectionMarshaller.class)
	public Direction getAngle() {
		return angle;
	}

	public void setAngle(Direction angle) {
		this.angle = angle;
	}

	public String toString(){
		return xpos + "," + ypos + "," + angle;
	}
}
