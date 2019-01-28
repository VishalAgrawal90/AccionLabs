package com.dao;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.data.TestData;
import com.mapper.VehicleRowMapper;
import com.model.Vehicle;

@RunWith(MockitoJUnitRunner.class)
@Configuration
public class InventoryDAOTest {

	@Mock
	private DataSource dataSource;
	
	@Mock 
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	VehicleInventoryDAO vehicleInventoryDAO;
	
	
	@Value("#{InventoryQueries['selectQuery']}")
	private String selectQuery;
	
	@Value("#{InventoryQueries['searchVehicleByIdQuery']}")
	private String searchVehicleByIdQuery;
	
	@Value("#{InventoryQueries['deleteVehicleByIdQuery']}")
	private String deleteVehicleByIdQuery;
	
	
	 @Before
	 public void initMocks() throws SQLException{
	        
	     }

	@Test
	public void testGetAllVehiclel() throws SQLException {
		Vehicle vehicle = TestData.getTestData();
		vehicle.setId(1);
		List<Vehicle> listOfVehicle = new ArrayList<Vehicle>();
		listOfVehicle.add(vehicle);
		when(jdbcTemplate.query(anyString(),any(VehicleRowMapper.class))).thenReturn(listOfVehicle);
		List<Vehicle> list = vehicleInventoryDAO.getAllVehicles();
		assertTrue(!list.isEmpty());		
	}
	
	@Test
	public void testAddVehicle() throws SQLException {
		when(jdbcTemplate.update(anyString(), anyObject(), anyObject())).thenReturn(1);
		int id = vehicleInventoryDAO.addVehicle(TestData.getTestData());
		assertSame(1, id);		
	}
	
	@Test
	public void testUpdateVehicle() throws SQLException {
		when(jdbcTemplate.update(anyString())).thenReturn(1);
		int id = vehicleInventoryDAO.updateVehicle(TestData.getTestData(),1);
		assertSame(1, id);		
	}
	
	@Test
	public void testDeleteLatestVehicle() throws SQLException {
		when(jdbcTemplate.update(anyString())).thenReturn(1);
		int id = vehicleInventoryDAO.deleteLatestVehicle();
		assertSame(1, id);		
	}
	
	@Test
	public void testDeleteVehicleById() throws SQLException {
		when(jdbcTemplate.update(deleteVehicleByIdQuery,new Object[] {1})).thenReturn(1);
		int id = vehicleInventoryDAO.deleteVehicleById(1);
		assertSame(1, id);		
	}
	
	@Test
	public void testSearchVehicleById() throws SQLException {
		Vehicle vehicle = TestData.getTestData();
		vehicle.setId(1);
		List<Vehicle> listOfVehicle = new ArrayList<Vehicle>();
		listOfVehicle.add(vehicle);
		List<Vehicle> listOfVehicles = vehicleInventoryDAO.searchVehicleById(1);
		assertTrue(listOfVehicles.isEmpty());	
	}
}
