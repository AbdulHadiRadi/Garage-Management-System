/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.SimpleDateProperty;

/**
 *
 * @author Sajjad
 */
public class Bookings {

    private final SimpleIntegerProperty BookingID;
    private final SimpleStringProperty firstname;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty reg;
    private final SimpleStringProperty make;
    private final SimpleStringProperty bookingdate;
    private final SimpleStringProperty bookingtime;
   // private final SimpleStringProperty repairtime;
    private final SimpleStringProperty nextbookingdate;
    private final SimpleIntegerProperty mechanicID;
    private final SimpleStringProperty bookingtype;
   // private final SimpleIntegerProperty mileage;
   // private final SimpleIntegerProperty cost;

    // private final Simple 
    Bookings(int ID,String fname,String Surname,String regplate, String Model,String Bookingdate, String Bookingtime,String NextbookingD, int mid, String type){
      //  System.out.println(BookID);
        this.BookingID = new SimpleIntegerProperty(ID);
     //   System.out.println(this.BookingID);
        this.firstname = new SimpleStringProperty(fname);
        this.surname = new SimpleStringProperty(Surname);
        this.reg = new SimpleStringProperty(regplate);
        this.make = new SimpleStringProperty(Model);
        this.bookingdate = new SimpleStringProperty(Bookingdate);
        this.bookingtime = new SimpleStringProperty(Bookingtime);
     //   this.repairtime = new SimpleStringProperty(repair);
        this.nextbookingdate = new SimpleStringProperty(NextbookingD);
        this.mechanicID = new SimpleIntegerProperty(mid);
        this.bookingtype = new SimpleStringProperty(type);
     //   this.mileage = new SimpleIntegerProperty(miles);
      //  this.cost= new SimpleIntegerProperty(costt);

    }

    public int getID() {
        return BookingID.get();
    }

    public void setID(int BookIDd) {
        BookingID.set(BookIDd);
    }

    public String getFirstName() {
        return firstname.get();
    }

    public void setFirstName(String Fname) {

        firstname.set(Fname);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String Sname) {

        surname.set(Sname);

    }

    public String getReg() {
        return reg.get();
    }

    public void setReg(String Regg) {

        reg.set(Regg);

    }

    public String getMaker() {
        return make.get();
    }

    public void setMaker(String mod) {

        make.set(mod);

    }

    public String getDate() {
        return bookingdate.get();
    }

    public void setDate(String datee) {

        bookingdate.set(datee);

    }

    public String getTime() {
        return bookingtime.get();
    }

    public void setTime(String time) {

        bookingtime.set(time);

    }

    /*public String getRepairDuration() {
        return repairtime.get();
    }

    public void setRepairDuration(String rtime) {

        repairtime.set(rtime);

    }*/
    
    public String getNextBooking(){
    return nextbookingdate.get();
    }
    
    public void setNextBooking(String date){
    
    nextbookingdate.set(date);
    }

    public int getMechanicID() {
        return mechanicID.get();
    }

    public void setMechanicID(int Mechid) {

        mechanicID.set(Mechid);

    }
    
    public String getType(){
        
    return bookingtype.get();
    }
    public void setType(String m){
    bookingtype.set(m);
    }
    
}
