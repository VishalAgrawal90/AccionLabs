                                               *Vehicle Inventory Management API Documentation*

Please follow the below steps to access API-

1.To Add the new Vehicle:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/
Request Method: POST
Request Body:  
 {
      "brand": "Aston Martin",
      "model": "V8",
      "color": "Grey",
      "price": "$1894603",
      "vehicleType": "Car",
      "description": "Aston Martin V8 Vantage"
  }
Headers:
Content-type: application/json
Accept: application/json
Http Status: 201 Created
Response:
{
    "message": "Added Successfully!",
    "statusCode": 201
}



2.To update an existing Vehicle:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/{vehicleId}
Expexted: http://localhost:8080/InventoryManagement/vehicles/1
Request Method: PUT
Request Body:  
 {
      "brand": "Aston Martin",
      "model": "V8",
      "color": "Grey",
      "price": "$1894603",
      "vehicleType": "Car",
      "description": "Aston Martin V8 Vantage"
  }
Headers:
Content-type: application/json
Accept: application/json
Http Status: 200 OK
Response:
{
    "message": "Updated Successfully!",
    "statusCode": 200
}


3.To delete last added Vehicle:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/delete/recent
Request Method: DELETE
Request Body: none
Headers:
Content-type: application/json
Accept: application/json
Http Status: 200 OK
Response:
{
    "message": "Deleted Successfully!",
    "statusCode": 200
}

4.To delete an existing Vehicle:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/delete/{vehicleId}
Expexted: http://localhost:8080/InventoryManagement/vehicles/delete/1
Request Method: DELETE
Request Body: none
Headers:
Content-type: application/json
Accept: application/json
Http Status: 200 OK
Response:
{
    "message": "Deleted Successfully!",
    "statusCode": 200
}

5.To get the list of all the available vehicles:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/
Request Method: GET
Request Body: none
Headers:
Content-type: application/json
Accept: application/json
Http Status: 200 OK

Response:
 [
    {
	    "id": "1",
        "brand": "Tesla",
        "model": "S",
        "price": "$78,000",
        "color": "Red",
        "vehicleType": "Car",
        "description": "Tesla Car"
    },
    {
	    "id": "2",
        "brand": "Range Rover",
        "model": "Sport",
        "price": "$84204",
        "color": "Grey",
        "vehicleType": "Car",
        "description": "Range Rover discovery sport car"
    }
]

6.To search an existing vehicle by vehicle id:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/{vehicleId}
Expexted: http://localhost:8080/InventoryManagement/vehicles/3
Request Method: GET
Request Body: none
Headers:
Content-type: application/json
Accept: application/json
Http Status: 200 OK
Response:
[
    {
	    "id": "3",
        "brand": "Aston Martin",
        "model": "V8",
        "color": "black",
        "price": "$1894603",
        "vehicleType": "Car",
        "description": "Aston Martin V8 Vantage"
        
    }
]

7.To search an existing vehicle with vehicle properties:-
 
URL:http://localhost:8080/InventoryManagement/vehicles/search/{Id}&{model}&{brand}&{price}&{color}&{vehicleType}&{description}
Expexted:http://localhost:8080/InventoryManagement/vehicles/search?brand=Aston Martin&model=V8&price=$1894603&color=black&vehicleType=Car&description=Aston Martin V8 Vantage
Request Method: GET
Request Body: none
Headers:
Content-type: application/json
Accept: application/json
Http Status: 200 OK
Response:
[
    {
	    "id": "3",
        "brand": "Aston Martin",
        "model": "V8",
        "price": "$1894603",
        "color": "black",
        "vehicleType": "Car",
        "description": "Aston Martin V8 Vantage"
        
    }
]