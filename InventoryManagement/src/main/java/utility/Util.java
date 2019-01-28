package utility;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import com.enums.VehicleType;
import com.exception.InvalidRquestDataException;
import com.exception.InvalidVehicleTypeException;
import com.model.Vehicle;

import constants.Constant;

/**
 * @author vishal
 *
 */
public class Util {
	Logger logger = LogManager.getLogger(Util.class);

	/** 
	 * @input id and reuestType ex:- 1,Create
	 * @return Response entity with StatusCode/Message
	 */
	public static Response getStatus(int id,String requestType) {
		if(id>0 && requestType.equals(Constant.addService)) 
    		return Response.status(Status.CREATED).entity(Constant.statusAdded).build();
		if(id>0 && requestType.equals(Constant.updateService)) 
    		return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(Constant.statusUpdated).build();
		if(id>0 && requestType.equals(Constant.deleteService)) 
    		return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(Constant.statusDelete).build();
    	
		return Response.status(Status.EXPECTATION_FAILED).type(MediaType.APPLICATION_JSON).entity(Constant.statusException).build();
	}
	
	/** Generate select query based on input parameters
	 * @input vehicle object
	 * @return select query
	 */
	public static String searchQueryBuiler(Vehicle vehicle) {
		StringBuilder searchQuery = new StringBuilder();
		searchQuery.append("SELECT * FROM VEHICLE WHERE");
		if(vehicle.getBrand()!=null)
			searchQuery.append(" "+Constant.vehiclebrand+"= '"+vehicle.getBrand()+"' "+Constant.and);
		if(vehicle.getModel()!=null)
			searchQuery.append(" "+Constant.vehicleModel+"= '"+vehicle.getModel()+"' "+Constant.and);
		if(vehicle.getColor()!=null)	
			searchQuery.append(" "+Constant.vehicleColor+"= '"+vehicle.getColor()+"' "+Constant.and);
		if(vehicle.getPrice()!=null)
			searchQuery.append(" "+Constant.vehiclePrice+"= '"+vehicle.getPrice()+"' "+Constant.and);	
		if(vehicle.getVehicleType()!=null)
			searchQuery.append(" "+Constant.vehicleType+"= '"+vehicle.getVehicleType()+"' "+Constant.and);		
		if(vehicle.getDescription()!=null)
			searchQuery.append(" "+Constant.vehicleDescription+"= '"+vehicle.getDescription()+"' "+Constant.and);
		
		String query = searchQuery.substring(0,searchQuery.lastIndexOf(" ")+1);
		return query;
	}
	
	/** Generate update query based on input parameters
	 * @input vehicle object
	 * @return update query
	 */
	public static String updateQueryBuiler(Vehicle vehicle,int vehicleId) {
		StringBuilder searchQuery = new StringBuilder();
		searchQuery.append("UPDATE VEHICLE SET");
		if(vehicle.getBrand()!=null)
			searchQuery.append(" "+Constant.vehiclebrand+"= '"+vehicle.getBrand()+"' "+Constant.comma);
		if(vehicle.getModel()!=null)
			searchQuery.append(" "+Constant.vehicleModel+"= '"+vehicle.getModel()+"' "+Constant.comma);
		if(vehicle.getColor()!=null)	
			searchQuery.append(" "+Constant.vehicleColor+"= '"+vehicle.getColor()+"' "+Constant.comma);
		if(vehicle.getPrice()!=null)
			searchQuery.append(" "+Constant.vehiclePrice+"= '"+vehicle.getPrice()+"' "+Constant.comma);	
		if(vehicle.getVehicleType()!=null)
			searchQuery.append(" "+Constant.vehicleType+"= '"+vehicle.getVehicleType()+"' "+Constant.comma);		
		if(vehicle.getDescription()!=null)
			searchQuery.append(" "+Constant.vehicleDescription+"= '"+vehicle.getDescription()+"' "+Constant.comma);
		
		String query = searchQuery.substring(0,searchQuery.lastIndexOf(" ")+1);
		query= query.concat("WHERE ID="+vehicleId);
		return query;
	}
	
	/** Generate select query based on input parameters
	 * @input vehicle object
	 * @return select query
	 */
	public static String searchQueryBuiler(UriInfo vehicle) {
		StringBuilder searchQuery = new StringBuilder();
		
		searchQuery.append("SELECT * FROM VEHICLE WHERE");
		if(vehicle.getQueryParameters().get(Constant.vehiclebrand)!=null)
			searchQuery.append(" "+Constant.vehiclebrand+"= '"+vehicle.getQueryParameters().get(Constant.vehiclebrand).get(0)+"' "+Constant.and);
		if(vehicle.getQueryParameters().get(Constant.vehicleModel)!=null)
			searchQuery.append(" "+Constant.vehicleModel+"= '"+vehicle.getQueryParameters().get(Constant.vehicleModel).get(0)+"' "+Constant.and);
		if(vehicle.getQueryParameters().get(Constant.vehicleColor)!=null)	
			searchQuery.append(" "+Constant.vehicleColor+"= '"+vehicle.getQueryParameters().get(Constant.vehicleColor).get(0)+"' "+Constant.and);
		if(vehicle.getQueryParameters().get(Constant.vehiclePrice)!=null)
			searchQuery.append(" "+Constant.vehiclePrice+"= '"+vehicle.getQueryParameters().get(Constant.vehiclePrice).get(0)+"' "+Constant.and);	
		if(vehicle.getQueryParameters().get(Constant.vehicleType)!=null)
			searchQuery.append(" "+Constant.vehicleType+"= '"+vehicle.getQueryParameters().get(Constant.vehicleType).get(0)+"' "+Constant.and);		
		if(vehicle.getQueryParameters().get(Constant.vehicleDescription)!=null)
			searchQuery.append(" "+Constant.vehicleDescription+"= '"+vehicle.getQueryParameters().get(Constant.vehicleDescription).get(0)+"' "+Constant.and);
		if(vehicle.getQueryParameters().get(Constant.vehicleId)!=null)
			searchQuery.append(" "+Constant.vehicleId+"= "+vehicle.getQueryParameters().get(Constant.vehicleId).get(0)+" "+Constant.and);
		
		String query = searchQuery.substring(0,searchQuery.lastIndexOf(" ")+1);
		return query;
	}
	
	/** validate vehicle field's is/are not null && vehicle type is valid
	 * @input vehicle object
	 * @return boolean
	 * @throws InvalidRquestDataException or InvalidVehicleTypeException
	 */
	public static boolean validateRequest(Vehicle vehicle) {
		if(StringUtils.isEmpty(vehicle.getBrand())|| StringUtils.isEmpty(vehicle.getColor())|| StringUtils.isEmpty(vehicle.getModel()) || StringUtils.isEmpty(vehicle.getPrice())
				|| StringUtils.isEmpty(vehicle.getVehicleType())|| StringUtils.isEmpty(vehicle.getDescription()))
			throw new InvalidRquestDataException();
		
			return validateVehicleType(vehicle.getVehicleType());
	}
	
	/** validate vehicle field's is/are not null && vehicle type is valid
	 * @input vehicle object
	 * @return boolean
	 * @throws InvalidRquestDataException or InvalidVehicleTypeException
	 */
	public static boolean validateUpdateRequest(Vehicle vehicle,int vehicleId) {
		if(!StringUtils.isEmpty(vehicle.getBrand())|| !StringUtils.isEmpty(vehicle.getColor())|| !StringUtils.isEmpty(vehicle.getModel()) 
		   ||! StringUtils.isEmpty(vehicle.getPrice())|| !StringUtils.isEmpty(vehicle.getVehicleType())|| !StringUtils.isEmpty(vehicle.getDescription())) {
			if(!StringUtils.isEmpty(vehicle.getVehicleType()))
				return validateVehicleType(vehicle.getVehicleType());
		}else
			 throw new InvalidRquestDataException();
		return true;
	}
	
	/** vehicle type is valid or not
	 * @input String vehicleType
	 * @return boolean
	 * @throws InvalidRquestDataException or InvalidVehicleTypeException
	 */
	public static boolean validateVehicleType(String vehicleType) {
		try {
				if(vehicleType!=null)
					VehicleType.valueOf(vehicleType.toUpperCase());
				else
					throw new InvalidRquestDataException();
		}catch(IllegalArgumentException e) {
			throw new InvalidVehicleTypeException();
		}
		return true;
	}
	
	/** validate vehicle field's is/are not null && vehicle type is valid
	 * @input vehicle object
	 * @return boolean
	 * @throws InvalidRquestDataException or InvalidVehicleTypeException
	 */
	public static boolean validateSearchRequest(UriInfo vehicle) {
		if(!StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehicleModel))  || !StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehiclebrand))
		  || !StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehiclePrice)) || !StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehicleColor))
		  || !StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehicleType))  || !StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehicleDescription))
		  || !StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehicleId))) {
				if(!StringUtils.isEmpty(vehicle.getQueryParameters().get(Constant.vehicleType)))
					return validateVehicleType(vehicle.getQueryParameters().get(Constant.vehicleType).get(0));
		}else
			throw new InvalidRquestDataException();
		
		return true;
	}
	
	
}
