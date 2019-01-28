package com.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.exception.InvalidRquestDataException;
import com.model.StatusDetails;

/**
 * @author vishal
 *
 */
@Provider
public class InvalidRquestDataExceptionClassMapper implements ExceptionMapper<InvalidRquestDataException>{

	@Override
	public Response toResponse(InvalidRquestDataException exception) {
		StatusDetails details = new StatusDetails(400, "Some field's data is/are missing in request");
		return Response.status(Status.BAD_REQUEST).entity(details).build();
	}

}
