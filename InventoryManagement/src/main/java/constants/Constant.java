package constants;

import com.model.StatusDetails;

/**
 * @author vishal
 *
 */
public class Constant {
	
	//Request Constants
	public static final String vehiclebrand="brand";
	public static final String vehicleModel="model";
	public static final String vehiclePrice="price";
	public static final String vehicleColor="color";
	public static final String vehicleType="vehicleType";
	public static final String vehicleDescription="description";
	public static final String vehicleId="id";

	//Delimiter
	public static final String comma=",";
	
	//Aggregate Functions
	public static final String and="and";
	
	// Response Status Code
	public static final StatusDetails statusAdded = new StatusDetails(201, "Added Successfully!");
	public static final StatusDetails statusUpdated = new StatusDetails(200, "Updated Successfully!");
	public static final StatusDetails statusDelete = new StatusDetails(200, "Deleted Successfully!");
	public static final StatusDetails statusException = new StatusDetails(417, "Expectation Failed");
	
	//H2 db details
	public static final String dbName="test";
	public static final String scriptEncoding="UTF-8";
	public static final String schemaSQLLocation="db/schema.sql";
	public static final String insertSQLLocation="db/insert.sql";
	
	//Services constants
	public static final String addService="add";
	public static final String updateService="update";
	public static final String deleteService="delete";
	
	
	//Message constants
	public static final String noDataFoundMsg="No data found";
	public static final String vehicleTypeObj= "Car,Truck,Airplane,Drone,Boat";
	
}
