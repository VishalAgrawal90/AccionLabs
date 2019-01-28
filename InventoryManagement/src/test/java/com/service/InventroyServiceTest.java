package com.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dao.IVehicleInventoryDAO;
import com.data.TestData;
import com.model.Vehicle;

import constants.Constant;

public class InventroyServiceTest {
	@InjectMocks 
	VehicleInventoryService vehicleInventoryService;
	
	@Mock
	IVehicleInventoryDAO iVehicleInventoryDAO;
	
	@Mock
	Response rs;
	
	 @Before
	 public void initMocks(){
	         MockitoAnnotations.initMocks(this);
	     }


	@Test
	public void testGetAllVehiclel() {
		List<Vehicle> listOfVehicle = new ArrayList<Vehicle>();
		listOfVehicle.add(TestData.getTestData());
		when(iVehicleInventoryDAO.getAllVehicles()).thenReturn(listOfVehicle);
		List<Vehicle> list = vehicleInventoryService.getAllVehicles();
		assertTrue(!list.isEmpty());	
	}
	
	
	@Test
	public void testaddVehiclel() {
		when(iVehicleInventoryDAO.addVehicle(TestData.getTestData())).thenReturn(anyInt());
		Response response = vehicleInventoryService.addVehicle(TestData.getTestData());
		assertNotNull(response);
	}
	
	@Test
	public void testupdateVehiclel() {
		Vehicle vehicle = TestData.getTestData();
		vehicle.setId(3);
		when(iVehicleInventoryDAO.updateVehicle(vehicle,1)).thenReturn(1);
		Response response = vehicleInventoryService.updateVehicle(vehicle,1);
		assertNotNull(response);
	}
	
	@Test
	public void testdeleteVehiclelById() {
		rs = Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(Constant.statusDelete).build();
		when(iVehicleInventoryDAO.deleteVehicleById(1)).thenReturn(1);
		Response response = vehicleInventoryService.deleteVehicleById(1);
		assertNotNull(response);
		
	}
	
	@Test
	public void testSearchVehiclelById() {
		List<Vehicle> listOfVehicle = new ArrayList<Vehicle>();
		listOfVehicle.add(TestData.getTestData());
		when(iVehicleInventoryDAO.searchVehicleById(1)).thenReturn(listOfVehicle);
		List<Vehicle> vehicles = vehicleInventoryService.searchVehicleById(1);
		assertTrue(!vehicles.isEmpty());
	}
}
