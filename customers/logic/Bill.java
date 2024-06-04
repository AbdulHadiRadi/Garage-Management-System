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
public class Bill {
    private IntegerProperty billID;
    private IntegerProperty billBookingID;
    private IntegerProperty totalCost;
    private StringProperty billStatus;



    public Bill(ResultSet rs) {
        try
        {
        this.billID = new SimpleIntegerProperty(rs.getInt("bill_ID"));
        this.billBookingID = new SimpleIntegerProperty(rs.getInt("bookingID"));
        this.totalCost = new SimpleIntegerProperty(rs.getInt("LabourCost")+(rs.getInt("totalPartCost"))+(rs.getInt("bookingCost")));
        if (rs.getInt("status") == 1)
            {
                this.billStatus = new SimpleStringProperty("Paid");
            }
            else
            {
                this.billStatus = new SimpleStringProperty("Not Paid");
            }

        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }




    public int getBillID() {return billID.get();}

    public IntegerProperty billIDProperty() {return billID;}

    public void setBillID(int billID) {this.billID.set(billID);}



    public int getBillBookingID() {
        return billBookingID.get();
    }

    public IntegerProperty billBookingIDProperty() {
        return billBookingID;
    }

    public void setBillBookingID(int billBookingID) {
        this.billBookingID.set(billBookingID);
    }



    public int getTotalCost() {return totalCost.get();}

    public IntegerProperty totalCostProperty() {return totalCost;}

    public void setTotalCost(int totalCost) {this.totalCost.set(totalCost);}


    public String getBillStatus() {
        return billStatus.get();
    }

    public StringProperty billStatusProperty() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus.set(billStatus);
    }
}
