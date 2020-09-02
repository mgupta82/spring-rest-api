package com.test.webapi.service;

import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;

public interface RobotService {
	
	public boolean createRobot(String name,Grid grid);
	
	public Position reportRobotPosition(String name);
	
	public boolean placeOnGrid(String name,Position position);
	
	public void executeCommand(String name, String command);
	
	public String[] getRobots();
}
