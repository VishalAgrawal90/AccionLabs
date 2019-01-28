package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IVehicleInventoryDAO;
import com.exception.GenericException;
import com.exception.InvalidRquestDataException;
import com.exception.InvalidVehicleTypeException;
import com.exception.NoDataFoundException;
import com.model.Vehicle;

import constants.Constant;
import utility.Util;

/**
 * @author vishal
 *
 */
@Service
@Transactional
public class VehicleInventoryService implements IVehicleInventoryService {
	Logger logger = LogManager.getLogger(VehicleInventoryService.class);

	@Autowired
	private IVehicleInventoryDAO iVehicleInventoryDAO;

	/**
	 * add provided vehicle to the db
	 * @input - vehicle object
	 * @return Response entity with StatusCode/Message
	 * @throws GenericException
	 */
	public Response addVehicle(Vehicle vehicle) {
		try {
			    Util.validateRequest(vehicle);
				int id = iVehicleInventoryDAO.addVehicle(vehicle);
				return Util.getStatus(id, Constant.addService);
		} catch (InvalidRquestDataException e) {
			logger.error(e.getMessage());
			throw new InvalidRquestDataException();
		}catch (InvalidVehicleTypeException e) {
			logger.error(e.getMessage());
			throw new InvalidVehicleTypeException();
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new NoDataFoundException(e);
		}

	}

	/**
	 * update provided vehicle to the db
	 * @input - vehicle object
	 * @return Response entity with StatusCode/Message
	 * @throws NoDataFoundException or GenericException
	 */
	public Response updateVehicle(Vehicle vehicle,int vehicleId) {
		try {
			 Util.validateUpdateRequest(vehicle,vehicleId);
			int id = iVehicleInventoryDAO.updateVehicle(vehicle,vehicleId);
			if (id <= 0)
				throw new NoDataFoundException();
			return Util.getStatus(id, Constant.updateService);
		} catch (InvalidRquestDataException e) {
			logger.error(e.getMessage());
			throw new InvalidRquestDataException();
		}catch (InvalidVehicleTypeException e) {
			logger.error(e.getMessage());
			throw new InvalidVehicleTypeException();
		}catch(NoDataFoundException e) {
			logger.error("No data found exception");
			throw new NoDataFoundException();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException();
		}
	}

	/**
	 * delete the last record from db
	 * @return Response entity with StatusCode/Message
	 * @throws GenericException
	 */
	
	@Override
	public Response deleteLatestVehicle() {
		try {
			int id=iVehicleInventoryDAO.deleteLatestVehicle();
			if(id>0)
			return Util.getStatus(1, Constant.deleteService);
			else
				throw new NoDataFoundException();
		} catch(NoDataFoundException e) {
			logger.error(Constant.noDataFoundMsg);
			throw new NoDataFoundException();
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException();
		}
	}

	/**
	 * delete the record from db based on vehicleID
	 * @return Response entity with StatusCode/Message
	 * @throws GenericException
	 */
	@Override
	public Response deleteVehicleById(int vehicleId) {
		try {
			int id = iVehicleInventoryDAO.deleteVehicleById(vehicleId);
			if(id>0)
				return Util.getStatus(id, Constant.deleteService);
			else
				throw new NoDataFoundException();
		} catch(NoDataFoundException e) {
			logger.error(Constant.noDataFoundMsg);
			throw new NoDataFoundException();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException();
		}
	}
	
	/**
	 * fetch the vehicles from db
	 * @return List of Vehicles
	 * @throws NoDataFoundExceptionException or GenericException
	 */
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> listOfVehicles =  new ArrayList<>();
		try {
			listOfVehicles = iVehicleInventoryDAO.getAllVehicles();
			if (listOfVehicles.isEmpty())
					throw new NoDataFoundException();
		} catch(NoDataFoundException e) {
			logger.error(Constant.noDataFoundMsg);
			throw new NoDataFoundException();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException();
		}
		return listOfVehicles;
	}

	/**
	 * search vehicle to the db based on provided vehicle Id
	 * @input - vehicle object
	 * @return Vehicle Object
	 * @throws GenericException
	 */
	@Override
	public List<Vehicle> searchVehicleById(int vehicleId) {
		try {
			List<Vehicle> vehicle = iVehicleInventoryDAO.searchVehicleById(vehicleId);
			if(vehicle.isEmpty())
				throw new NoDataFoundException();
			return vehicle;
		}catch(NoDataFoundException e) {
			logger.error(Constant.noDataFoundMsg);
			throw new NoDataFoundException();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException();
		}
	}

	@Override
	public List<Vehicle> searchVehicle(UriInfo vehicle) {
		List<Vehicle> listOfVehicles =  new ArrayList<>();
		try {
			listOfVehicles = iVehicleInventoryDAO.searchVehicle(vehicle);
			if (listOfVehicles.isEmpty())
					throw new NoDataFoundException();
		}catch (InvalidRquestDataException e) {
			logger.error(e.getMessage());
			throw new InvalidRquestDataException();
		}catch (InvalidVehicleTypeException e) {
			logger.error(e.getMessage());
			throw new InvalidVehicleTypeException();
		} catch(NoDataFoundException e) {
			logger.error(Constant.noDataFoundMsg);
			throw new NoDataFoundException();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			throw new GenericException();
		}
		return listOfVehicles;
	}

}
