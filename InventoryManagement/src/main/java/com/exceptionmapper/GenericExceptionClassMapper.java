package com.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.exception.GenericException;
import com.model.StatusDetails;
/**
 * @author Vishal
 *
 */
@Provider
public class GenericExceptionClassMapper implements ExceptionMapper<GenericException>{

	/** create the StatusDetails class object 
	 * @return Response entity object
	 *
	 */
	@Override
	public Response toResponse(GenericException exception) {
		StatusDetails details = new StatusDetails(417, "Expectation Failed");
		return Response.status(Status.EXPECTATION_FAILED).entity(details).build();
	}

}
