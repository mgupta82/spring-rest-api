package com.test.webapi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;

public class DirectionMarshaller implements DynamoDBMarshaller<Direction> {

	@Override
	public String marshall(Direction direction) {
		// TODO Auto-generated method stub
		return direction.name();
	}

	@Override
	public Direction unmarshall(Class<Direction> clazz, String obj) {
		// TODO Auto-generated method stub
		return Direction.valueOf(obj);
	}

}
