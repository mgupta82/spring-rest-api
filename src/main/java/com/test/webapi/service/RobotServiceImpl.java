package com.test.webapi.service;

import com.test.webapi.dao.RobotDao;
import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;
import com.test.webapi.model.Robot;

public class RobotServiceImpl extends Service implements RobotService {

	@Override
	public boolean createRobot(String name, Grid grid) {
		RobotDao dao = (RobotDao) getDao(RobotDao.class);
		Robot robot = dao.getRobot(name);
		if(robot == null){
			dao.createRobot(name, grid);
			return true;
		}
		return false;
	}

	@Override
	public Position reportRobotPosition(String name) {
		RobotDao dao = (RobotDao) getDao(RobotDao.class);
		Robot robot = dao.getRobot(name);
		if(robot != null)
			return dao.getRobot(name).getPosition();
		return null;
	}

	@Override
	public boolean placeOnGrid(String name, Position position) {
		RobotDao dao = (RobotDao) getDao(RobotDao.class);
		return dao.updatePosition(name, position);
	}
	
	@Override
	public void executeCommand(String name, String command){
		RobotDao dao = (RobotDao) getDao(RobotDao.class);
		Robot robot = dao.getRobot(name);
		if(robot == null || robot.getPosition() == null)
			return;
		switch(command){
			case "left":robot.turnLeft();
						break;
			case "right":robot.turnRight();
						break;
			case "move":robot.moveForward();
						break;			
		}
		
		dao.updateRobot(robot);
	}

	@Override
	public String[] getRobots() {
		RobotDao dao = (RobotDao) getDao(RobotDao.class);
		return dao.getRobots().toArray(new String[0]);
	}

}
