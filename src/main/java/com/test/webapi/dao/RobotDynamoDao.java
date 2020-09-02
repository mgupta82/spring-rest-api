package com.test.webapi.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;
import com.test.webapi.model.Robot;

public class RobotDynamoDao extends DynamoDao implements RobotDao {
	
	public void createRobot(String name, Grid grid){
		Robot robot = new Robot(name, grid);
		getMapper().save(robot);
	}
	
	public Robot getRobot(String name){
		DynamoDBMapperConfig config = new DynamoDBMapperConfig(
				    DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
		Robot robot = getMapper().load(Robot.class,name,config);
		return robot;
	}	
	
	public boolean updatePosition(String name, Position position){
		Robot robot = getRobot(name);
		if(robot!=null 
				&& position.getXpos() < robot.getGrid().getBreadth()-1 
				&& position.getYpos()<robot.getGrid().getLength()-1){
			robot.setPosition(position);
			getMapper().save(robot);
			return true;
		}
		return false;
	}	
	
	public void updateRobot(Robot robot){
		getMapper().save(robot);
	}
	
	public void deleteRobot(String name){
		getMapper().delete(getRobot(name));
	}	

	@Override
	public List<String> getRobots() {
		Table table = getDynamoDB().getTable("Robots");
  
        ScanSpec scanSpec = new ScanSpec()
                .withProjectionExpression("#name")
                .withNameMap(new NameMap().with("#name",  "name"));        
        
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);
        Iterator<Item> iter = items.iterator();
        Item item = null;
        List<String> robots = new ArrayList<String>();
        while (iter.hasNext()) {
            item = iter.next();
            robots.add(item.getString("name"));
        }        
		return robots;
	}

}
