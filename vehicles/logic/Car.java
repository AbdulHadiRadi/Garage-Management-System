/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.logic;

/**
 *
 * @author abdul
 */
public class Car extends Vehicle{
     //Constructor
    public Car(String type, String  registrationNumber, String model, String make, double engineSize, String fuelType, String colour, String MoTRenewalDate, String lastServiceDate, int currentMileage, String hasWarranty, int customerID) {
        super(type, registrationNumber, model, make, engineSize, fuelType, colour, MoTRenewalDate, lastServiceDate, currentMileage, hasWarranty, customerID); 
    }
}