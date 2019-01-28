create table vehicle 
( 
   id integer auto_increment, 
   brand varchar(255) not null, 
   model varchar(255) not null, 
   price varchar(255)  not null, 
   color varchar(255) not null, 
   vehicleType varchar(255) not null, 
   description varchar(255), 
   primary key(id) 
);