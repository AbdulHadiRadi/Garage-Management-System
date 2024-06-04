package customers.logic;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Fahim on 19/02/2017.
 */
public class Part {
    private IntegerProperty part_UsedID;
    private StringProperty dateInstalled;
    private IntegerProperty partID;
    private IntegerProperty booking_id;
    private StringProperty warrExp;
    private StringProperty reg_num;



    public Part(ResultSet rs)
    {
        try{
            this.part_UsedID = new SimpleIntegerProperty(rs.getInt("PartUsedID"));
            this.dateInstalled = new SimpleStringProperty(rs.getString("DateInstalled"));
            this.partID = new SimpleIntegerProperty(rs.getInt("PartID"));
            this.booking_id = new SimpleIntegerProperty(rs.getInt("bookingID"));
            this.warrExp = new SimpleStringProperty(rs.getString("WarrantyExpiry"));
            this.reg_num = new SimpleStringProperty(rs.getString("regNumber"));

        }catch (SQLException e)
        {

            e.printStackTrace();
        }

    }

    public String getReg_num() {return reg_num.get();}

    public StringProperty reg_numProperty() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num.set(reg_num);
    }

    public String getWarrExp() {
        return warrExp.get();
    }

    public StringProperty warrExpProperty() {
        return warrExp;
    }

    public void setWarrExp(String warrExp) {
        this.warrExp.set(warrExp);
    }

    public int getBooking_id() {
        return booking_id.get();
    }

    public IntegerProperty booking_idProperty() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id.set(booking_id);
    }


    public int getPart_UsedID() {return part_UsedID.get();}

    public IntegerProperty part_UsedIDProperty() {return part_UsedID;}

    public void setPart_UsedID(int part_UsedID) {this.part_UsedID.set(part_UsedID);}

    public String getDateInstalled() {return dateInstalled.get();}

    public StringProperty dateInstalledProperty() {return dateInstalled;}

    public void setDateInstalled(String dateInstalled) {this.dateInstalled.set(dateInstalled);}

    public int getPartID() {return partID.get();}

    public IntegerProperty partIDProperty() {return partID;}

    public void setPartID(int partID) {this.partID.set(partID);}

}

