package com.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.model.Vehicle;
import com.service.IVehicleInventoryService;

/**
 * @author Vishal
 *
 */

@Path("/vehicles")
public class VehicleInventoryController {
	Logger logger = LogManager.getLogger(VehicleInventoryController.class);

	@Autowired
	IVehicleInventoryService iVehicleInventoryService;

	/**
	 * add provided vehicle to the db
	 * @method_type - @Post
	 * @Produce Media_Type- Application_XML/APPLICATION_JSON
	 * @return Response entity with StatusCode/Message
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addVehicle(Vehicle vehicle) {
		return iVehicleInventoryService.addVehicle(vehicle);
	}

	/**
	 * update vehicle details
	 * @method_type - @Put
	 * @Consume Media_Type- Application_XML/APPLICATION_JSON
	 * @return Response entity with StatusCode/Message
	 */
	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateVehicle(@PathParam("id") int vehicleId,Vehicle vehicle) {
		return iVehicleInventoryService.updateVehicle(vehicle,vehicleId);
	}

	/**
	 * delete the last added vehicle
	 * @method_type - @Delete
	 * @Consume Media_Type- Application_XML/APPLICATION_JSON
	 * @return Response entity with StatusCode/Message
	 */
	@DELETE
	public Response deleteRecentVehicle() {
		return iVehicleInventoryService.deleteLatestVehicle();
	}
	
	/**
	 * delete vehicle based on vehicle ID
	 * @method_type - @Delete
	 * @Consume Media_Type- Application_XML/APPLICATION_JSON
	 * @return Response entity with StatusCode/Message
	 */
	@Path("{id}")
	@DELETE
	public Response deleteVehicleById(@PathParam("id")int vehicleId) {
		return iVehicleInventoryService.deleteVehicleById(vehicleId);
	}
	
	/**
	 * search vehicle based on the input provide
	 * @method_type - @Get
	 * @Consume Media_Type- Application_XML/APPLICATION_JSON
	 * @return List of Vehicles
	 */
	@GET
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Vehicle> searchVehicleById(@PathParam("id")int vehicleId) {
		return iVehicleInventoryService.searchVehicleById(vehicleId);
	}
	
	/**
	 * fetch the vehicles from db
	 * @method_type - @Get
	 * @Produce Media_Type- Application_XML/APPLICATION_JSON
	 * @return List of Vehicles
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> listOfVehicles = iVehicleInventoryService.getAllVehicles();
		return listOfVehicles;
	}
	/**
	 * search vehicle based on the input provide
	 * @method_type - @Get
	 * @Consume Media_Type- Application_XML/APPLICATION_JSON
	 * @return List of Vehicles
	 */
	@Path("/search")
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Vehicle> searchVehicle(@Context UriInfo vehicle) {
		return iVehicleInventoryService.searchVehicle(vehicle);
	}
}
