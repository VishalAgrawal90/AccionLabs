package com.model;

/**
 * @author Vishal
 *
 */
public class StatusDetails {

	private int statusCode;
	private String message;
	
	public StatusDetails() {
		super();
	}

	public StatusDetails(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
