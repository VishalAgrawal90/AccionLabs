package com.service;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.model.Vehicle;

/**
 * @author vishal
 */
public interface IVehicleInventoryService {

	public List<Vehicle> getAllVehicles();
	public List<Vehicle> searchVehicleById(int vehicleId);
	public List<Vehicle> searchVehicle(UriInfo vehicle);
    public Response addVehicle(Vehicle vehicle);
    public Response updateVehicle(Vehicle vehicle,int vehicleId);
    public Response deleteLatestVehicle();
    public Response deleteVehicleById(int vehicleId);

}
