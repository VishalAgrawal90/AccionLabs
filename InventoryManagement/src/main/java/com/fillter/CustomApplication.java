package com.fillter;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author vishal
 *
 */
public class CustomApplication extends ResourceConfig{

	public CustomApplication() {
		packages("com");
	}
}
