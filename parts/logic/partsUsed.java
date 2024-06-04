package parts.logic;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.text.DecimalFormat;

/**
 *
 * @author Wajid
 */
// class to get part installed information for parts module and SPC module
public class partsUsed {

    private SimpleIntegerProperty PartUsedID;
    private SimpleStringProperty name;
    private SimpleStringProperty DateInstalled;
    private SimpleStringProperty WarrantyExpiry;
    private SimpleIntegerProperty Quantity;
    private SimpleStringProperty totalPartCost;
    private SimpleIntegerProperty bookingIDUsed;

    private SimpleDoubleProperty Cost;

    private SimpleIntegerProperty fPartID;
    private SimpleIntegerProperty fBookingID;
    private SimpleStringProperty fDescription;

    // to format double total cost to 2 decimal place
    private DecimalFormat df = new DecimalFormat(".##");

    public partsUsed(int PartUsedID1, String name1, String DateInstalled1, String WarrantyExpiry1, int Quantity1, double totalPartCost1, int bookingIDUsed1) {
        this.PartUsedID = new SimpleIntegerProperty(PartUsedID1);
        this.name = new SimpleStringProperty(name1);
        this.DateInstalled = new SimpleStringProperty(DateInstalled1);
        this.WarrantyExpiry = new SimpleStringProperty(WarrantyExpiry1);
        this.Quantity = new SimpleIntegerProperty(Quantity1);
        this.totalPartCost = new SimpleStringProperty(df.format(totalPartCost1));
        this.bookingIDUsed = new SimpleIntegerProperty(bookingIDUsed1);

    }

    public partsUsed(int PartUsedID1, String name1, String DateInstalled1, String WarrantyExpiry1, Double Cost1, int partId, int bookingId, String fdes) {
        this.PartUsedID = new SimpleIntegerProperty(PartUsedID1);
        this.name = new SimpleStringProperty(name1);
        this.DateInstalled = new SimpleStringProperty(DateInstalled1);
        this.WarrantyExpiry = new SimpleStringProperty(WarrantyExpiry1);
        this.Cost = new SimpleDoubleProperty(Cost1);
        this.fPartID = new SimpleIntegerProperty(partId);
        this.fBookingID = new SimpleIntegerProperty(bookingId);
        this.fDescription = new SimpleStringProperty(fdes);

    }

    public partsUsed(int PartUsedID1, String name1, String DateInstalled1, String WarrantyExpiry1, Double Cost1, int Quantity1) {
        this.PartUsedID = new SimpleIntegerProperty(PartUsedID1);
        this.name = new SimpleStringProperty(name1);
        this.DateInstalled = new SimpleStringProperty(DateInstalled1);
        this.WarrantyExpiry = new SimpleStringProperty(WarrantyExpiry1);
        this.Cost = new SimpleDoubleProperty(Cost1);
        this.Quantity = new SimpleIntegerProperty(Quantity1);

    }

    public int getPuID() {
        return PartUsedID.get();
    }

    public int getPartUsedID() {
        return PartUsedID.get();
    }

    public void setPuID(int PartUsedID1) {
        PartUsedID.set(PartUsedID1);

    }

    public String getName() {
        return name.get();
    }

    public String getPuName() {
        return name.get();
    }

    public void setPuName(String name1) {
        name.set(name1);

    }

    public String getDateInstalled() {
        return DateInstalled.get();
    }

    public void setDateInstalled1(String DateInstalled1) {
        DateInstalled.set(DateInstalled1);
    }

    public String getWExpiry() {
        return WarrantyExpiry.get();
    }

    public String getWarrantyExpiry() {
        return WarrantyExpiry.get();
    }

    public void setWEpiry1(String WarrantyExpiry1) {
        WarrantyExpiry.set(WarrantyExpiry1);
    }

    public int getQuan() {
        return Quantity.get();
    }

    public int getQuantity() {
        return Quantity.get();
    }

    public String getTotalCost() {
        return totalPartCost.get();
    }

    public String getTotalPartCost() {
        return totalPartCost.get();
    }

    public int getBIDused() {
        return bookingIDUsed.get();
    }

    public int getBookingIDUsed() {
        return bookingIDUsed.get();
    }

    public int getFPartID() {
        return fPartID.get();
    }

    public int getFBookingID() {
        return fBookingID.get();
    }

    public String getFDescription() {
        return fDescription.get();
    }

}
