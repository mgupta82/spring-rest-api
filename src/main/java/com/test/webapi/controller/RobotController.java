package com.test.webapi.controller;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.test.webapi.model.Grid;
import com.test.webapi.model.Position;
import com.test.webapi.service.RobotService;
import com.test.webapi.service.ServiceFactory;

@Path("/robot")
public class RobotController {
	
	static final ServiceFactory factory = new ServiceFactory();
	
	@Context UriInfo uriInfo;

	@POST
	@Path("/{name}")
	public Response create(@PathParam("name") String name){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		if(service.createRobot(name, new Grid(5,5)))
			return Response.status(200).entity("Robot Created - "+name).build();
		return Response.status(303).entity("Robot already exists - "+name).build();
	}	

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] list(){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		return service.getRobots();
	}	
	
	@POST
	@Path("/{name}/position")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response place(@PathParam("name") String name,Position position){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		if(service.placeOnGrid(name, position))
			return Response.status(200).entity("Robot Placed at "+position).build();
		return Response.status(412).entity("Robot not created yet - "+name).build();
	}	
	
	@PUT
	@Path("/{name}/position/{command}")
	public Response change(@PathParam("name") String name,@PathParam("command") String command){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		service.executeCommand(name, command);
		URI uri =  uriInfo.getAbsolutePath();
		Response r;
		r = Response.created(uri).build();
		return r;
	}
	
	@GET
	@Path("/{name}/position")
	@Produces(MediaType.APPLICATION_JSON)
	public Position report(@PathParam("name") String name){
		RobotService service = (RobotService) factory.getService(RobotService.class);
		return service.reportRobotPosition(name);
	}
	
}
