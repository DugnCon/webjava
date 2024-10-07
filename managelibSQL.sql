USE library;
CREATE TABLE IF NOT EXISTS signup(
ID INT AUTO_INCREMENT NOT NULL,
userName VARCHAR(205) NOT NULL UNIQUE,
passWord VARCHAR(205) NOT NULL,
repeatPassWord VARCHAR(205) NOT NULL,
PRIMARY KEY (ID),
UNIQUE (userName)
);
CREATE TABLE IF NOT EXISTS book (
bookID INT NOT NULL AUTO_INCREMENT,     
bookCode VARCHAR(50) NOT NULL UNIQUE,   
title VARCHAR(255) NOT NULL,            
chapter INT,                             
author VARCHAR(205) NOT NULL,         
genre VARCHAR(205) NOT NULL,            
publisher VARCHAR(205) NOT NULL,      
releaseYear YEAR NOT NULL,             
quantity INT DEFAULT 1,                  
PRIMARY KEY (bookID)                    
);
CREATE TABLE IF NOT EXISTS user(
userID INT NOT NULL AUTO_INCREMENT,
userName VARCHAR(205) NOT NULL,
passWord VARCHAR(205) NOT NULL,
creatAc TIMESTAMP NOT NULL,
PRIMARY KEY (userID),
FOREIGN KEY (userName) REFERENCES signup(userName) ON UPDATE CASCADE	
);
CREATE TABLE IF NOT EXISTS borrower(
borrowerID INT NOT NULL AUTO_INCREMENT,
userID INT NOT NULL,
bookCode VARCHAR(205) NOT NULL,
borrowDate DATE NOT NULL,
returnDate DATE NOT NULL,
PRIMARY KEY (borrowerID),
FOREIGN KEY (userID) REFERENCES user(userID) ON UPDATE CASCADE,
FOREIGN KEY (bookCode) REFERENCES book(bookCode) ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS payer(
payerID INT NOT NULL AUTO_INCREMENT,
userID INT NOT NULL,
amount DECIMAL(10,2) NOT NULL,
paymentDate DATE NOT NULL,
PRIMARY KEY (payerID),
FOREIGN KEY (userID) REFERENCES user(userID) ON UPDATE CASCADE
);
CREATE TABLE inventory (
inventoryID INT NOT NULL AUTO_INCREMENT,   
bookCode VARCHAR(50) NOT NULL,             
quantity INT NOT NULL,                      
status ENUM('available', 'borrowed', 'reserved') DEFAULT 'available',  
PRIMARY KEY (inventoryID),                  
FOREIGN KEY (bookCode) REFERENCES book(bookCode) ON UPDATE CASCADE 
);

CREATE TABLE employees (
employeeID INT NOT NULL AUTO_INCREMENT,   
name VARCHAR(100) NOT NULL,               
position VARCHAR(50) NOT NULL,
hireDate DATE NOT NULL,                   
salary DECIMAL(10, 2) NOT NULL,           
inventoryID INT,                         
PRIMARY KEY (employeeID),                 
FOREIGN KEY (inventoryID) REFERENCES inventory(inventoryID) ON UPDATE CASCADE
);
