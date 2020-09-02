package com.test.webapi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Robots")
public class Robot {
	
	String name;
	Position position;
	Grid grid;
	
	public Robot(){
		super();
	}		
	
	public Robot(String name,Grid grid){
		this.name = name;
		this.grid = grid;
	}	
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
			this.position = position;
	}

	public Grid getGrid() {
		return grid;
	}
	
	@DynamoDBHashKey
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void stepEast(){
		if(position.xpos < grid.getBreadth()-1){
			position.xpos++;
		}	
	}
	
	private void stepNorth(){
		if(position.ypos < grid.getLength()-1){
			position.ypos++;
		}			
	}	
	
	private void stepWest(){
		if(position.xpos > -1){
			position.xpos--;
		}		
	}	
	
	private void stepSouth(){
		if(position.ypos > -1){
			position.ypos--;
		}			
	}
	
	public void moveForward() {
		if(this.getPosition()!=null){
			switch(this.getPosition().getAngle()){
				case NORTH: stepNorth();
							break;
				case SOUTH: stepSouth();
							break;
				case EAST:  stepEast();
							break;
				case WEST:  stepWest();
							break;
			}
		}
	}

	public void turnLeft() {
		if(this.getPosition()!=null){
			switch(this.getPosition().getAngle()){
				case NORTH: this.getPosition().setAngle(Direction.WEST);
							break;
				case SOUTH: this.getPosition().setAngle(Direction.EAST);
							break;
				case EAST:  this.getPosition().setAngle(Direction.NORTH);
							break;
				case WEST:  this.getPosition().setAngle(Direction.SOUTH);
							break;
			}
		}
	}

	public void turnRight() {
		if(this.getPosition()!=null){
			switch(this.getPosition().getAngle()){
			case NORTH: this.getPosition().setAngle(Direction.EAST);
						break;
			case SOUTH: this.getPosition().setAngle(Direction.WEST);
						break;
			case EAST:  this.getPosition().setAngle(Direction.SOUTH);
						break;
			case WEST:  this.getPosition().setAngle(Direction.NORTH);
						break;
			}
		}
	}	

	public String toString(){
		return "Name:" +name + " Grid:" + grid + " Position:"+position;
	}
}
