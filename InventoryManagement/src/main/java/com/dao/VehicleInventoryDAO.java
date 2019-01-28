package com.dao;

import java.sql.Types;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mapper.VehicleRowMapper;
import com.model.Vehicle;

import utility.Util;

/**
 * @author vishal
 *
 */
@Repository
public class VehicleInventoryDAO implements IVehicleInventoryDAO{
	Logger logger = LogManager.getLogger(VehicleInventoryDAO.class);
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Value("#{InventoryQueries['selectAllVehicleQuery']}")
	private String selectAllVehicleQuery;
	
	@Value("#{InventoryQueries['insertVehicleQuery']}")
	private String insertVehicleQuery;
	
	@Value("#{InventoryQueries['deleteRecentVehilceQuery']}")
	private String deleteRecentVehilceQuery;

	@Value("#{InventoryQueries['deleteVehicleByIdQuery']}")
	private String deleteVehicleByIdQuery;
	
	@Value("#{InventoryQueries['searchVehicleByIdQuery']}")
	private String searchVehicleByIdQuery;
	

	/**
	 * fetch the vehicles from db
	 * @return List of Vehicles
	 */
	public List<Vehicle> getAllVehicles() {
		return jdbcTemplate.query(selectAllVehicleQuery, new VehicleRowMapper());
	}
	
	/**
	 * add provided vehicle to the db
	 * @input - vehicle object
	 * @return int value
	 */
	public int addVehicle(Vehicle vehicle) {
		
		int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		Object[] values = new Object[]{vehicle.getBrand(), vehicle.getModel(),vehicle.getPrice(),vehicle.getColor(),vehicle.getVehicleType(),vehicle.getDescription()};
		return jdbcTemplate.update(insertVehicleQuery, values, types);
		
	}

	/**
	 * update provided vehicle to the db
	 * @input - vehicle object
	 * @return int value
	 */
	public int updateVehicle(Vehicle vehicle,int vehicleId) {
			String updateQuery =  Util.updateQueryBuiler(vehicle,vehicleId);
		    return jdbcTemplate.update(updateQuery);
		
	}

	/**
	 * delete the last added record from db
	 */
	@Override
	public int deleteLatestVehicle() {
		return jdbcTemplate.update(deleteRecentVehilceQuery);
		
	}

	/**
	 * delete record from db based on vehicle id
	 */
	@Override
	public int deleteVehicleById(int vehicleId) {
		return jdbcTemplate.update(deleteVehicleByIdQuery, new Object[] { vehicleId });
	}

	/**
	 * search vehicle based on vehicleId
	 * @input - vehicle id
	 * @return List of the vehicles
	 */
	@Override
	public List<Vehicle> searchVehicleById(int vehicleId) {
		return jdbcTemplate.query(searchVehicleByIdQuery, new Object[] { vehicleId },new VehicleRowMapper());
	}

	@Override
	public List<Vehicle> searchVehicle(UriInfo vehicle) {
		Util.validateSearchRequest(vehicle);
		String searchQuery = Util.searchQueryBuiler(vehicle);
		return jdbcTemplate.query(searchQuery, new VehicleRowMapper());
	}



}
