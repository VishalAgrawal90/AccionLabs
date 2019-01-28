package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Vehicle;

import constants.Constant;

/**
 * @author Vishal
 *
 */
public class VehicleRowMapper implements RowMapper<Vehicle> {
	
	/** fetching the record from resutset
	 * @input ResultSet and rowNum
	 * @return Vehicle entity class object
	 */
	public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(rs.getInt(Constant.vehicleId));
		vehicle.setBrand(rs.getString(Constant.vehiclebrand));
		vehicle.setColor(rs.getString(Constant.vehicleColor));
		vehicle.setModel(rs.getString(Constant.vehicleModel));
		vehicle.setPrice(rs.getString(Constant.vehiclePrice));
		vehicle.setVehicleType(rs.getString(Constant.vehicleType));
		vehicle.setDescription(rs.getString(Constant.vehicleDescription));
		return vehicle;
	}

}
