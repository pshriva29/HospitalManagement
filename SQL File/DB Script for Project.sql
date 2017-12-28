# Pragati Shrivastava
# Hospital Management System

CREATE DATABASE pshrivas_db; -- Database name

use pshrivas_db;

/* Doctor Table Creation */

CREATE TABLE Doctor(
doctorID INT NOT NULL AUTO_INCREMENT,name VARCHAR(100),contactNo VARCHAR(15),specialization VARCHAR(25),PRIMARY KEY (doctorID)
);


/* Insert values for Doctor */

INSERT INTO Doctor (name,contactNo,specialization)
VALUES ('Ajay Sharma', '3124561111', 'Flu'),
('Amol Shrivastava', '3124562222', 'Stomach Infection'),
('Priyanka Jain', '3124563333', 'Pediatrician'),
('Dipti Sharma', '3124564444', 'Dermatalogy');

INSERT INTO Doctor (name,contactNo,specialization)
VALUES ('Steve Mathhews', '3124565555', 'Cardiologist'),
('John Doe', '3124566666', 'Flu and Cold'),
('Jane Doe', '3124567777', 'Allergist'),
('Mradul Modi', '3124568888', 'Neurologist');


/* Patient Table Creation */

CREATE TABLE Patient(
patientID INT NOT NULL AUTO_INCREMENT,name VARCHAR(100),address VARCHAR(100),gender VARCHAR(10),DOB date,phnNo VARCHAR(15),illness VARCHAR(100),illnessFee DECIMAL(7, 2),doctorID INT,
admissionDate date,dischargeDate date,roomNo INT,PRIMARY KEY (patientID),FOREIGN KEY (doctorID) REFERENCES Doctor(doctorID)
);

/* Values inserted in Patient through front end application */

/* Bill Table Creation */

CREATE TABLE Bill(
billNo INT NOT NULL AUTO_INCREMENT,patientId INT,amount DECIMAL(7, 2),billDate date,billPaid VARCHAR(5),PRIMARY KEY (billNo),
FOREIGN KEY (patientId) REFERENCES Patient(patientId)
);

/* Values are inserted in Bill Table through front end application */

/* Users Table Creation */

CREATE TABLE Users(
userName VARCHAR(40), password VARCHAR(40), userID INT, PRIMARY KEY (userName)
);

/* Values inserted in Users through front end application */

/* PatientHistory Table Creation */

CREATE TABLE PatientHistory(
patientID INT NOT NULL,name VARCHAR(100),illness VARCHAR(100),doctorID INT,
admissionDate date,dischargeDate date,PRIMARY KEY (patientID)
);

/* Values inserted in PatientHistory through front end application */







