/*
* @author Abdul Hadi
* This class represents Vehicle object
*/
package vehicles.logic;


public class Vehicle {
        //Instance Variables declaration
	private String vehicleType;
	private String registrationNumber;
	private String model;
	private String make;
	private double engineSize;
	private String fuelType;
	private String colour;
	private String MoTRenewalDate;
	private String lastServiceDate;
	private int currentMileage;
	private String hasWarranty;
	private int customerID;
         //Constructor
	public Vehicle() {

	}
         //Constructor
	public Vehicle(String type, String  registrationNumber, String model, String make, double engineSize, String fuelType, String colour, String MoTRenewalDate, String lastServiceDate, int currentMileage, String hasWarranty, int customerID) {
		this.vehicleType = type;
		this.registrationNumber = registrationNumber;
		this.model = model;
		this.make = make;
		this.engineSize = engineSize;
		this.fuelType = fuelType;
		this.colour = colour;
		this.MoTRenewalDate = MoTRenewalDate;
		this.lastServiceDate = lastServiceDate;
		this.currentMileage = currentMileage;
		this.hasWarranty = hasWarranty;
		this.customerID = customerID;
	}
        
         //GETTER AND SETTER METHOD OF INSTANCE VARIABLES
	public String  getRegistrationNumber() {
		return this.registrationNumber;
	}


	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getModel() {
		return this.model;
	}

	public String getMake() {
		return this.make;
	}

	public double getEngineSize() {
		return this.engineSize;
	}

	public String getFuelType() {
		return this.fuelType;
	}

	public String getColour() {
		return this.colour;
	}

	public String getMoTRenewalDate() {
		return MoTRenewalDate;
	}

	public String getLastServiceDate() {

		return this.lastServiceDate;
	}

	public int getCurrentMileage() {

		return this.currentMileage;
	}

	public String getHasWarranty() {

		return this.hasWarranty;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setMake(String make) { this.make = make; }

	public void setEngineSize(double engineSize) {
		this.engineSize = engineSize;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setMoTRenewalDate(String moTRenewalDate) {
		MoTRenewalDate = moTRenewalDate;
	}

	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public void setCurrentMileage(int currentMileage) {
		this.currentMileage = currentMileage;
	}

	public void setHasWarranty(String  hasWarranty) {
		this.hasWarranty = hasWarranty;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


}
