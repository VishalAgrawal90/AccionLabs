package com.data;

import com.model.Vehicle;

public class TestData {

	public static Vehicle getTestData(){
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("Aston Martin");
		vehicle.setModel("v8");
		vehicle.setColor("Metallic Grey");
		vehicle.setPrice("$1894603");
		vehicle.setVehicleType("Car");
		vehicle.setDescription("Aston Martin V8 Vantage");
		return vehicle;
	}
}
