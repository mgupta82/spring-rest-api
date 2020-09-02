# Toy Robot problem statement 
The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units. There are no other obstructions on the table surface. 
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. 
Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed. 
Create an application that can read in commands of the following form – 
* PLACE X,Y,F - Where PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. The origin (0,0) can be considered to be the SOUTH WEST most corner. It is required that the first command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed.  
* MOVE - move the toy robot one unit forward in the direction it is currently facing. 
* LEFT - will rotate the robot 90 degrees in the specified direction without changing the position of the robot. 
* RIGHT - will rotate the robot 90 degrees in the specified direction without changing the position of the robot. 
* REPORT - will announce the X,Y and F of the robot.

A robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands. 

##Constraints: 
The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot. Any move that would cause the robot to fall must be ignored.

##Example 
* e.g. 1
PLACE 0,0,
NORTH 
MOVE 
REPORT Output: 0,1,NORTH 
* e.g. 2
PLACE 0,0,
NORTH 
LEFT 
REPORT Output: 0,0,WEST 
* e.g. 3
PLACE 1,2,EAST 
MOVE 
MOVE 
LEFT 
MOVE 
REPORT Output: 3,3,NORTH

##REST Interface
Traditionally the toy robot responds to text input files, but to make the toy robot work on the web, it should be exposed as a RESTful web service. The task is to prepare a RESTful web service wrapper around a toy robot, as well as implement the toy robot. The web service should implement the API documented below. The test is designed to test your knowledge of java coding and of web applications. 

The API is quite simple. Please document any assumptions you make completing the exercise or any problems and workarounds you encounter.

Please include test and build scripts to easily demonstrate the service working. Please make sure all source code is available for review.

Please note the reference API is hosted on a resource limited AWS instance and as such may take a little while to reply to initial requests. Your app should handle this potential failure scenario in a sensible way.

The exercise is to implement RESTful api for a toy robot. The use cases to support are
* create a new robot with a name
* place a robot on the default table with a specified orientation and position
* direct the robot to turn left, turn right or move.
* report on the robot’s position

##Non Functional Requirements
* The app should handle failure scenarios.
* The app should support CORS.
* The solution should be should straight forward to build and run.
* The solution should include testing.
* There may be concurrent users of the API.

##Example
A simple example of interacting with the service from the command line is shown below
* curl -X POST -i -H "Content-type: text/html" http://toyrobotservice-env.elasticbeanstalk.com/webapi/robot/megatron
* curl -X POST -i -H "Content-type: application/json" http://toyrobotservice-env.elasticbeanstalk.com/webapi/robot/megatron/position -d '{"angle":"WEST", "x_pos":2, "y_pos":2}'
* curl -X PUT -i http://toyrobotservice-env.elasticbeanstalk.com/webapi/robot/megatron/position/left 
* curl -X PUT -i http://toyrobotservice-env.elasticbeanstalk.com/webapi/robot/megatron/position/right 
* curl -X PUT -i http://toyrobotservice-env.elasticbeanstalk.com/webapi/robot/megatron/position/move 
* curl -X GET -i http://toyrobotservice-env.elasticbeanstalk.com/webapi/robot/megatron/position/

##REST API Details

###Robot
An instance of a robot. Has a name, an optional table, and a position.
* create : POST /robot/robot_name - Initialises a new robot. Returns a 303 if the robot already exists.
* list : GET /robot/list - Returns a JSON array of robot names.

###Position
A vector describing the location an orientation of the robot.
The JSON representation of a position is { “angle” : String, “x_pos” : int, “y_pos” : int }. Valid angle strings are NORTH SOUTH EAST WEST
* place : POST /robot/robot_name/position - Place the robot on the default table. Message body must contain a JSON object specifying the position and orientation.
* change : PUT /robot/ robot_name /position/command - submit instructions to turn or move the robot. Valid commands are left, right, move. The new position is returned as a JSON object.
* report : GET /robot/ robot_name /position/positionId - Returns a JSON object specifying the position of the robot. If the robot has not been placed on the table, a 204 is returned.  

#Technical Setup

##Create Maven Project
mvn archetype:generate -DgroupId=com.test -DartifactId=webapi -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

##Database Setup
###Create Table Robots
var params = {
    TableName : "Robots",
    KeySchema: [       
        { AttributeName: "name", KeyType: "HASH"}
    ],
    AttributeDefinitions: [       
        { AttributeName: "name", AttributeType: "S" }
    ],
    ProvisionedThroughput: {       
        ReadCapacityUnits: 1, 
        WriteCapacityUnits: 1
    }
};

dynamodb.createTable(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###Create Robot
var params = {
    TableName: "Robots",
    Item: {
        "name":"test",
        "grid": {
            "length" : 5,
            "breadth": 5
        },    
        "position": {
            "xpos" : 2,
            "ypos" : 3,
            "angle": "NORTH"
        }
    }
};

dynamodb.putItem(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###Delete Table
var params = {
    TableName: "Robots"
};

dynamodb.deleteTable(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###Get Robot
var params = { 
    TableName: "Robots",
    Key: {
        "name": "test"
    }
};

dynamodb.getItem(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###Update position
var params = {
    TableName: "Robots",
    Key: {
        "name":"test"
    },
    UpdateExpression: "SET #position.xpos = :xpos, #position.ypos= :ypos, #position.angle = :angle",
    ExpressionAttributeValues: { 
        ":xpos": 3,
        ":ypos" : 4,
        ":angle" : "EAST"
    },
    ExpressionAttributeNames: {"#position": "position"},
    ReturnValues: "ALL_NEW"
};

dynamodb.updateItem(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###list robots
var params = {
    TableName: "Music"
};

dynamodb.scan(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###Increment xpos
var params = {
    TableName: "Robots",
    Key: {
        "name":"test"
    },
    UpdateExpression: "SET #position.xpos = #position.xpos + :incr",
    ExpressionAttributeValues: { 
        ":incr": 1
    },
    ExpressionAttributeNames: {"#position": "position"},
    ReturnValues: "UPDATED_NEW"
};

dynamodb.updateItem(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});

###Increment ypos
var params = {
    TableName: "Robots",
    Key: {
        "name":"test"
    },
    UpdateExpression: "SET #position.ypos = #position.ypos + :incr",
    ExpressionAttributeValues: { 
        ":incr": 1
    },
    ExpressionAttributeNames: {"#position": "position"},
    ReturnValues: "UPDATED_NEW"
};

dynamodb.updateItem(params, function(err, data) {
    if (err)
        console.log(JSON.stringify(err, null, 2));
    else
        console.log(JSON.stringify(data, null, 2));
});


