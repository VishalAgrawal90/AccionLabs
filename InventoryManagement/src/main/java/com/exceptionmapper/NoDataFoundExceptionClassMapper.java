package com.exceptionmapper;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.util.CollectionUtils;

import com.exception.NoDataFoundException;
import com.model.StatusDetails;

/**
 * @author Vishal
 *
 */
@Provider
public class NoDataFoundExceptionClassMapper implements ExceptionMapper<NoDataFoundException> {
	
	@Context
	private HttpHeaders headers;

	/** create the StatusDetails class object 
	 * @return Response entity object
	 *
	 */
	@Override
	public Response toResponse(NoDataFoundException exception) {
		StatusDetails details = new StatusDetails(404, "No Data found");
		return Response.status(Status.NOT_FOUND).entity(details).type(MediaType.APPLICATION_JSON).build();
	}

	/** fetching accepted mediType from header
	 * @return mediaType - Application_XML/Application_JSON
	 * @default mediaType- Application_JSON
	 *
	 */
	private MediaType getAcceptType() {
		List<MediaType> mediaTypes =headers.getAcceptableMediaTypes();
		if(!CollectionUtils.isEmpty(mediaTypes) && mediaTypes.size() >0) {
			return mediaTypes.get(0);
		}else {
			return MediaType.APPLICATION_JSON_TYPE;
		}
	}

}
