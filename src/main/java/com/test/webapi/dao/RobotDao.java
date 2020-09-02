package com.test.webapi.dao;

import java.util.List;

import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;
import com.test.webapi.model.Robot;

public interface RobotDao {
	
	public void createRobot(String name, Grid grid);
	public Robot getRobot(String name);
	public boolean updatePosition(String name, Position position);
	public void updateRobot(Robot robot);
	public List<String> getRobots();
	public void deleteRobot(String name);
}
