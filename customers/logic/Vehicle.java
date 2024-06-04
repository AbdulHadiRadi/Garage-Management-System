package customers.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Fahim on 19/02/2017.
 */
public class Vehicle {
    private StringProperty reg;
    private IntegerProperty customer_vehicle;
    private StringProperty model;
    private StringProperty make;
    private StringProperty size;
    private StringProperty fuel;
    private StringProperty colour;
    private StringProperty mot;
    private StringProperty last;
    private IntegerProperty mile;

    public Vehicle (ResultSet rs)
    {
        try {
            this.reg = new SimpleStringProperty(rs.getString("Registration_Number"));
            this.customer_vehicle = new SimpleIntegerProperty(rs.getInt("CustomerID"));
            this.model = new SimpleStringProperty(rs.getString("Model"));
            this.make = new SimpleStringProperty(rs.getString("Manufacturer"));
            this.size = new SimpleStringProperty(rs.getString("Engine_Size"));
            this.fuel = new SimpleStringProperty(rs.getString("Fuel_Type"));
            this.colour = new SimpleStringProperty(rs.getString("Colour"));
            this.mot = new SimpleStringProperty(rs.getString("MOT_Renewal_Date"));
            this.last = new SimpleStringProperty(rs.getString("Last_Service"));
            this.mile = new SimpleIntegerProperty(rs.getInt("Current_Mileage"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCustomer_vehicle() {
        return customer_vehicle.get();
    }

    public IntegerProperty customer_vehicleProperty() {
        return customer_vehicle;
    }

    public void setCustomer_vehicle(int customer_vehicle) {
        this.customer_vehicle.set(customer_vehicle);
    }

    public String getReg() {
        return reg.get();
    }

    public StringProperty regProperty() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg.set(reg);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getMake() {
        return make.get();
    }

    public StringProperty makeProperty() {
        return make;
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getFuel() {
        return fuel.get();
    }

    public StringProperty fuelProperty() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel.set(fuel);
    }

    public String getColour() {
        return colour.get();
    }

    public StringProperty colourProperty() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour.set(colour);
    }

    public String getMot() {
        return mot.get();
    }

    public StringProperty motProperty() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot.set(mot);
    }

    public String getLast() {
        return last.get();
    }

    public StringProperty lastProperty() {
        return last;
    }

    public void setLast(String last) {
        this.last.set(last);
    }

    public int getMile() {
        return mile.get();
    }

    public IntegerProperty mileProperty() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile.set(mile);
    }
}
