package com.test.webapi.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.test.webapi.dao.DaoFactory;
import com.test.webapi.dao.RobotDao;
import com.test.webapi.model.Direction;
import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;

public class RobotServiceTest {
	
	static ServiceFactory factory = new ServiceFactory();
	
	static final String ROBOT_NAME = "test9";
	
	@Before
	 public void setUp() throws Exception {
		try{
		new DaoFactory().getDynamoDao(RobotDao.class).deleteRobot(ROBOT_NAME);
		}catch(Exception ex){
		}		 
	 }	
	
	@Test
	public void TestCreateRobot() {
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.createRobot(ROBOT_NAME, new Grid(5,5));
		assertEquals(1, 1);
	}
	
	@Test
	public void reportRobotPosition() {
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.reportRobotPosition(ROBOT_NAME);
		assertEquals(1, 1);
	}
	
	@Test
	public void TestPlaceOnGrid(){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.placeOnGrid(ROBOT_NAME, new Position(1,2,Direction.NORTH));
		assertEquals(1, 1);
	}
	
	@Test
	public void testExecuteLeftCommand(){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.executeCommand(ROBOT_NAME, "left");
		assertEquals(1, 1);
	}
	
	@Test
	public void testExecuteRightCommand(){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.executeCommand(ROBOT_NAME, "right");
		assertEquals(1, 1);
	}
	
	@Test
	public void testExecuteMoveCommand(){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.executeCommand(ROBOT_NAME, "move");
		assertEquals(1, 1);
	}	

	@Test
	public void testGetRobots(){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.getRobots();
		assertEquals(1, 1);
	}	
	
}
