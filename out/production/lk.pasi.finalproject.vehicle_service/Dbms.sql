SHOW DATABASES;
DROP DATABASE IF EXISTS vehicle_service;
CREATE DATABASE vehicle_service;
USE vehicle_service;
CREATE TABLE Admin(
                      userId      VARCHAR(20) NOT NULL,
                      userName    VARCHAR(30) NOT NULL,
                      contact     VARCHAR(15),
                      password    VARCHAR(15),
                      CONSTRAINT PRIMARY KEY (userId)

);

CREATE TABLE Customer(
                         cId      VARCHAR(20) NOT NULL,
                         cName    VARCHAR(30),
                         cAddress VARCHAR(30),
                         contact  VARCHAR(15),
                         CONSTRAINT PRIMARY KEY (cId)
);

CREATE TABLE Stock(
                      itemId    VARCHAR(20) NOT NULL,
                      itemName  VARCHAR(50),
                      itemPrice DOUBLE,
                      itemQty   INT,
                      CONSTRAINT PRIMARY KEY (itemId)
);

CREATE TABLE Vehicle(
                        vehicleId     VARCHAR(20) NOT NULL,
                        vehicleName   VARCHAR(50),
                        vehicleColour VARCHAR(25),
                        customerId    VARCHAR(20) NOT NULL,
                        CONSTRAINT PRIMARY KEY (vehicleId),
                        CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (cId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Orderr(
                       itemId  VARCHAR(20) NOT NULL,
                       itemName VARCHAR(30) NOT NULL,
                       customerId VARCHAR(20) NOT NULL,
                       orderId VARCHAR(20) NOT NULL,
                       itemPrice DOUBLE,
                       amount INT,
                       CONSTRAINT PRIMARY KEY (orderId),
                       CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer (cId) ON DELETE CASCADE ON UPDATE CASCADE,
                       CONSTRAINT FOREIGN KEY (itemId) REFERENCES Stock (itemId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Stock_Order_Detail(
                                   itemId      VARCHAR(20) NOT NULL,
                                   orderId     VARCHAR(20) NOT NULL,
                                   CONSTRAINT FOREIGN KEY (itemId) REFERENCES Stock(itemId) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orderr(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE vehicle_Order_Detail(
                                     vehicleId   VARCHAR(20) NOT NULL ,
                                     orderId     VARCHAR(20) NOT NULL ,
                                     CONSTRAINT FOREIGN KEY (vehicleId) REFERENCES Vehicle(vehicleId) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orderr(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Repair(
                       repairId   VARCHAR(20) NOT NULL,
                       vehicleId  VARCHAR(20) NOT NULL,
                       date DATE,
                       customerId VARCHAR(20) NOT NULL,
                       CONSTRAINT PRIMARY KEY (repairId),
                       CONSTRAINT FOREIGN KEY (vehicleId) REFERENCES Vehicle (vehicleId) ON DELETE CASCADE ON UPDATE CASCADE,
                       CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer(cId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE repair_Item_Detail(
                                   repairId   VARCHAR(20) NOT NULL,
                                   itemId      VARCHAR(20) NOT NULL,
                                   CONSTRAINT FOREIGN KEY (repairId) REFERENCES Repair(repairId) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT FOREIGN KEY (itemId) REFERENCES Stock(itemId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Booking(
                        bookingId  VARCHAR(20) NOT NULL,
                        date DATE ,
                        time TIME ,
                        customerId VARCHAR(20) NOT NULL,
                        CONSTRAINT PRIMARY KEY (bookingId),
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

SHOW TABLES;

