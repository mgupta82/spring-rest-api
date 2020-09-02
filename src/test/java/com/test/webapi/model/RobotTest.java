package com.test.webapi.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RobotTest {
	
	@Test
	public void testRobot(){
		Robot robot = new Robot("test",new Grid(5, 5));
		robot.setPosition(new Position(1, 2, Direction.valueOf("EAST")));
		robot.moveForward();
		robot.moveForward();
		robot.turnLeft();
		robot.moveForward();
		assertEquals("3,3,NORTH", robot.getPosition().toString());
	}

}
