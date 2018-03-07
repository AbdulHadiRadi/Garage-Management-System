# Garage-Management-System

It was a group project of software Engineering module at 2nd year of my degree.

Implemented Language and Tools: Java, Javafx, SQLite.

Mthodolopy:  Waterfall methodology 
Design Pattern: Model–view–controller design pattern.

The system was Integrated by combining all the independently developed parts together.
There were 5 parts named Customer, Vehicle, Booking, Parts, Special Parts.
This project got source code of my part vehicle and common part of the project which was developed by me.  
 
This is a very good example of Java fxml project for a beginner.


 Authentication 
1. A GM-SIS system administrator will be allocated an elevated authentication
mechanism to allow them to setup and administer new and existing system
users and to act as a super-user to edit any data in the system.
2. Records of all system users will be stored in the system.
3. There are two types of system users: system administrators and day-to-day
users. Each will be identified by their employee surname, firstname and
unique 5-digit identification number (id).
4. Garage customers are NOT users of the system.
Software Engineering Project 2017
Page 5
5. A system user will logon using their id and a password. The password will be
allocated by the system administrator
6. Only the system administrator can add, edit or delete system users and change
passwords.
7. All system users can use all other modules in GM-SIS once they have logged
in using their correct id and password.
8. Failure to login correctly will result in the system offering a renewed
invitation to login again.
9. Checks shall be made to ensure that system users and ids are unique.
10. The administrator should be able to query the system to get a list of active
users and their details. They should be able to add, edit and delete directly
from this list.

 Vehicle record 
1. A vehicle is classified as a car, van, or truck.
2. A vehicle will either be under warranty or not. A warranty means that should a
vehicle require diagnosis and repair, the warranty company pays for this and
not the customer.
Software Engineering Project 2017
Page 6
3. If a vehicle is under warranty the name and address of the warranty company
and the date of expiry of the warranty must be recorded against the vehicle.
4. The details of each vehicle must be recorded by the garage: registration
number, model, make, engine size, fuel type, colour and MoT renewal date.
5. Date from last service and the current mileage must be recorded.
6. Associated with each vehicle will be lists of parts used, past and future
booking dates, and the total cost per booking (warranty and non-warranty).
This must tally against the customer account.
7. A list of 20 standard vehicle template, such as Honda Civic 1.6 Litre SE, or
Ford Focus 1.2 Litre, must be created and made available for quick selection
when a new vehicle is being booked in, including model, make, engine size
and fuel type.
8. The user should be able to query the system to get a list of vehicles with
customer details and next booking date. They should be able to add, edit and
delete directly from this list.
9. For each vehicle, the system user should be able to access lists of past and
future booking dates, vehicles and parts used per vehicle etc.
10. When deleting a vehicle a confirmation shall be required.
11. At least 15 vehicle records must be created and integrated with data from other
modules.
12. The user should be able to search for a vehicle based on partial or full
registration number (number plate) or manufacturer.

