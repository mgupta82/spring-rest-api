package com.test.webapi.dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class DynamoDao {
	
	public static DynamoDBMapper getMapper(){
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        //client.setEndpoint("http://localhost:8000");
		client.withRegion(Regions.AP_SOUTHEAST_2);
        return new DynamoDBMapper(client);
	}	
	
	public static DynamoDB getDynamoDB(){
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        //client.setEndpoint("http://localhost:8000");
		client.withRegion(Regions.AP_SOUTHEAST_2);
        return new DynamoDB(client);
	}	
	
}
