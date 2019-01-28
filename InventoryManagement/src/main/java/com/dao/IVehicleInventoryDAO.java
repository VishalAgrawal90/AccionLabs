package com.dao;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.model.Vehicle;

/**
 * @author vishal
 */
public interface IVehicleInventoryDAO {

	public List<Vehicle> getAllVehicles();
	public List<Vehicle> searchVehicleById(int vehicleId);
	public List<Vehicle> searchVehicle(UriInfo vehicle);
    public int addVehicle(Vehicle vehicle);
    public int updateVehicle(Vehicle vehicle,int vehicleId);
    public int deleteLatestVehicle();
    public int deleteVehicleById(int vehicleId);
}