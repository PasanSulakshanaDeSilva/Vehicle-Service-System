SHOW DATABASES;
DROP DATABASE IF EXISTS vehicle_service;
CREATE DATABASE vehicle_service;
USE vehicle_service;

CREATE TABLE Customer(
    cId      VARCHAR(20) NOT NULL,
    cName    VARCHAR(30),
    cAddress VARCHAR(30),
    contact  VARCHAR(15),
    CONSTRAINT PRIMARY KEY (cId)
);

CREATE TABLE Item(
    itemId    VARCHAR(20) NOT NULL,
    itemName  VARCHAR(50),
    itemPrice DOUBLE,
    itemQty   INT,
    CONSTRAINT PRIMARY KEY (itemId)
);

CREATE TABLE Order(
    orderId    VARCHAR(20) NOT NULL,
    itemId     VARCHAR(20) NOT NULL,
    customerId VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Vehicle(
    vehicleId     VARCHAR(20) NOT NULL,
    vehicleName   VARCHAR(50),
    vehicleColour VARCHAR(25),
    customerId    VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (vehicleId),
    CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (cId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Booking(
    bookingId  VARCHAR(20) NOT NULL,
    date DATE ,
    time TIME ,
    customerId VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (bookingId),
    CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (cId) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Repair(
    repairId   VARCHAR(20) NOT NULL,
    vehicleId  VARCHAR(20) NOT NULL,
    date DATE,
    customerId VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (vehicleId),
    CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (cId) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Payment(
    paymentId  VARCHAR (20) NOT NULL,
    customerId VARCHAR(20) NOT NULL,
    paymentFee DOUBLE,
    date DATE,
    CONSTRAINT PRIMARY KEY (paymentId),
    CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (cId) ON DELETE CASCADE ON UPDATE CASCADE
);
