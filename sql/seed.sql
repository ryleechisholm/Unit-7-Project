--blood bank before: drive dates, times, goals, truck
--blood bank after: number of donors
--donor database: id, name, type, address
--truck database: id, model, location

--create database bloodDrive;

create table trucks(id BIGSERIAL, model varchar(200), location varchar(200), primary key(truckID));

create table donors(id BIGSERIAL, name varchar(200), type varchar(20), number varchar(200), primary key(donorID));

create table drive(id BIGSERIAL, date varchar(100), time varchar(200), truck int, goal int, location varchar(255), actual int null, donors int null, primary key(driveID), foreign key(truck) references trucks(truckID));