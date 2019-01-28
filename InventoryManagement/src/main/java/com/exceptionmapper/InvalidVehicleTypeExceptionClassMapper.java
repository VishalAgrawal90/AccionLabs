package com.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.exception.InvalidVehicleTypeException;
import com.model.StatusDetails;

import constants.Constant;

/**
 * @author vishal
 *
 */
@Provider
public class InvalidVehicleTypeExceptionClassMapper implements ExceptionMapper<InvalidVehicleTypeException>{

	@Override
	public Response toResponse(InvalidVehicleTypeException exception) {
		StatusDetails details = new StatusDetails(400, "Vehicle type is not valid, supports: {"+Constant.vehicleTypeObj+"}") ;
		return Response.status(Status.BAD_REQUEST).entity(details).build();
	}

}
