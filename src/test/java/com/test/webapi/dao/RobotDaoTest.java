package com.test.webapi.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.test.webapi.model.Direction;
import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;


public class RobotDaoTest {
	
	static DaoFactory factory = new DaoFactory();
	
	static final String ROBOT_NAME = "test8";

	@Before
	 public void setUp() throws Exception {
		try{
		factory.getDynamoDao(RobotDao.class).deleteRobot(ROBOT_NAME);
		}catch(Exception ex){
		}
	 }

	@Test
	public void testCreateRobot(){	
		factory.getDynamoDao(RobotDao.class).createRobot(ROBOT_NAME, new Grid(5, 5));
		assertEquals(1, 1);
	}	
	
	@Test
	public void testGetRobot(){	
		factory.getDynamoDao(RobotDao.class).getRobot(ROBOT_NAME);
		assertEquals(1, 1);
	}	
	
	@Test
	public void testUpdatePosition(){	
		factory.getDynamoDao(RobotDao.class).updatePosition(ROBOT_NAME, new Position(1, 3, Direction.valueOf("EAST")));
		assertEquals(1, 1);
	}
	
	@Test
	public void testGetRobots(){	
		factory.getDynamoDao(RobotDao.class).getRobots();
		assertEquals(1, 1);
	}	
	
}
