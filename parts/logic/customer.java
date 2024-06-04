/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Wajid
 */
// customer class to get vehicle and customer info for selection model
public class customer {

    private SimpleStringProperty RegistrationNumber = null;
    private SimpleStringProperty FirstName = null;
    private SimpleStringProperty LastName = null;
    private SimpleStringProperty Address = null;
    private SimpleStringProperty Postcode = null;
    private SimpleStringProperty Email = null;
    private SimpleStringProperty Phone = null;

    // vehicle sector 
    private SimpleStringProperty Make = null;
    private SimpleStringProperty Model = null;
    private SimpleStringProperty Colour = null;
    private SimpleDoubleProperty EngineSize = null;
    private SimpleStringProperty FuelType = null;

    private SimpleStringProperty VehicleType = null;
    private SimpleStringProperty WarrantyStatus = null;
    private SimpleStringProperty MOTRenewalDate = null;
    private SimpleStringProperty LastService = null;
    private SimpleIntegerProperty CustomerID = null;
    private SimpleIntegerProperty CurrentMil = null;

    public customer(String Reg1, int CustomerID1, String Firstname1, String Surname1, String Address1, String Postcode1, String Phone1, String Email1, String Make1, String Model1,
            String Colour1, String Fuel1, String VType1, String WStatus1, String MRen1, double eng1, String lastServ1, int CurrentMil1) {

        this.RegistrationNumber = new SimpleStringProperty(Reg1);
        this.FirstName = new SimpleStringProperty(Firstname1);
        this.LastName = new SimpleStringProperty(Surname1);
        this.Address = new SimpleStringProperty(Address1);
        this.Postcode = new SimpleStringProperty(Postcode1);
        this.Email = new SimpleStringProperty(Email1);

        this.Make = new SimpleStringProperty(Make1);
        this.Model = new SimpleStringProperty(Model1);
        this.Colour = new SimpleStringProperty(Colour1);
        this.EngineSize = new SimpleDoubleProperty(eng1);
        this.FuelType = new SimpleStringProperty(Fuel1);
        this.VehicleType = new SimpleStringProperty(VType1);
        this.WarrantyStatus = new SimpleStringProperty(WStatus1);
        this.MOTRenewalDate = new SimpleStringProperty(MRen1);
        this.CustomerID = new SimpleIntegerProperty(CustomerID1);
        this.Phone = new SimpleStringProperty(Phone1);
        this.LastService = new SimpleStringProperty(lastServ1);
        this.CurrentMil = new SimpleIntegerProperty(CurrentMil1);
    }

    public customer() {

    }

    public String getReg() {
        return RegistrationNumber.get();
    }

    public int getCID() {
        return CustomerID.get();
    }

    public String getFName() {
        return FirstName.get();
    }

    public String getSName() {
        return LastName.get();
    }

    public String getAddress() {
        return Address.get();
    }

    public String getPostcode() {
        return Postcode.get();
    }

    public String getCusPhone() {
        return Phone.get();
    }

    public String getEmail() {
        return Email.get();
    }

    public String getMake() {
        return Make.get();
    }

    public String getModel() {
        return Model.get();
    }

    public String getColour() {
        return Colour.get();
    }

    public double getESize() {
        return EngineSize.get();
    }

    public String getFuel() {
        return FuelType.get();
    }

    public String getVType() {
        return VehicleType.get();
    }

    public String getWStatus() {
        return WarrantyStatus.get();
    }

    public String getMRen() {
        return MOTRenewalDate.get();
    }

    public String getLServ() {
        return LastService.get();
    }

    public int getCMile() {
        return CurrentMil.get();
    }

}
