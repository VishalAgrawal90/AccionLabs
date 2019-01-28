package com.utility;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.data.TestData;

import constants.Constant;
import utility.Util;

public class UtilTest {
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetStatus() {
		Response addResponse =Util.getStatus(1, Constant.addService);
		assertNotNull(addResponse);
		Response updateResponse =Util.getStatus(1, Constant.updateService);
		assertNotNull(updateResponse);
		Response deleteResponse =Util.getStatus(1, Constant.deleteService);
		assertNotNull(deleteResponse);
	}
	
	@Test
	public void testUpdateQueryBuiler() {
		String updateQuery=Util.updateQueryBuiler(TestData.getTestData(),1);
		assertNotNull(updateQuery);
	}
	
	@Test
	public void testValidateRequest() {
		boolean isValidRequest =Util.validateRequest(TestData.getTestData());
		assertTrue(isValidRequest);
	}
	
	@Test
	public void testValidateVehicleType() {
		boolean isVehicleType=Util.validateVehicleType("Car");
		assertTrue(isVehicleType);
	}
}
