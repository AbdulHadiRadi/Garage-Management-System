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
public class Booking {
    private IntegerProperty bookingID;
    private StringProperty bookingType;
    private IntegerProperty bookingDate;
    private StringProperty vehicleReg;
    private IntegerProperty mechanic;
    private IntegerProperty customerID;
    private IntegerProperty duration;
    private StringProperty bookingTime;
    private StringProperty nextBookingDate;
    private IntegerProperty labourCost;
    private IntegerProperty hoursSpent;



    public Booking(ResultSet rs)
    {
        try {
            this.bookingID = new SimpleIntegerProperty(rs.getInt("bookingID"));
            this.bookingType = new SimpleStringProperty(rs.getString("booking_type"));
            this.vehicleReg = new SimpleStringProperty(rs.getString("Registration_Number"));
            this.mechanic = new SimpleIntegerProperty(rs.getInt("MechanicID"));
            this.customerID = new SimpleIntegerProperty(rs.getInt("CustomerID"));
            this.nextBookingDate = new SimpleStringProperty(rs.getString("ReturnBookingDate"));
            this.labourCost = new SimpleIntegerProperty(rs.getInt("LabourCost"));
            this.hoursSpent = new SimpleIntegerProperty(rs.getInt("hoursSpent"));
            this.bookingDate = new SimpleIntegerProperty(rs.getInt("booking_date"));
            this.bookingTime = new SimpleStringProperty(rs.getString("Booking_time"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getBookingTime() {return bookingTime.get();}

    public StringProperty bookingTimeProperty() {return bookingTime;}

    public void setBookingTime(String bookingTime) {this.bookingTime.set(bookingTime);}

    public int getBookingDate() {return bookingDate.get();}

    public IntegerProperty bookingDateProperty() {return bookingDate;}

    public void setBookingDate(int bookingDate) {this.bookingDate.set(bookingDate);}

    public int getBookingID() {
        return bookingID.get();
    }

    public IntegerProperty bookingIDProperty() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {this.bookingID.set(bookingID);}

    public String getNextBookingDate() {return nextBookingDate.get();}

    public StringProperty nextBookingdateProperty() {
        return nextBookingDate;
    }

    public void setNextBookingDate(String NextBookingDate) {this.nextBookingDate.set(NextBookingDate);}

    public int getlabourCost() {return labourCost.get();}

    public IntegerProperty labourCostProperty() {
        return labourCost;
    }

    public void setlabourCost(Integer LabourCost) {this.hoursSpent.set(LabourCost);}

    public int gethoursSpent() {return hoursSpent.get();}

    public IntegerProperty hoursSpentProperty() {
        return hoursSpent;
    }

    public void sethoursSpent(Integer hoursSpent) {this.hoursSpent.set(hoursSpent);}

    public int getMechanic() {
        return mechanic.get();
    }

    public IntegerProperty mechanicProperty() {
        return mechanic;
    }

    public void setMechanic(Integer mechanic) {
        this.mechanic.set(mechanic);
    }


    public String getbookingType() {return bookingType.get();}

    public StringProperty bookingTypeProperty() {return bookingType;}

    public void setbookingType(String booking_type) {this.bookingType.set(booking_type);}

    public String getVehicleReg() {
        return vehicleReg.get();
    }

    public StringProperty vehicleRegProperty() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {this.vehicleReg.set(vehicleReg);}

    public int getcustomer() {return customerID.get();}

    public IntegerProperty customerProperty() {
        return customerID;
    }

    public void setcustomer(int customerID) {this.customerID.set(customerID);}



}
