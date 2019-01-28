package com.exception;

/**
 * @author Vishal
 *
 */
public class NoDataFoundException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoDataFoundException() {
		super();	
}
	
	public NoDataFoundException(Exception e) {
		super(e);	
}


}