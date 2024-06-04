/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Wajid
 */

// booking class to get booking info to add parts used to booking for repair
public class booking {

    private final SimpleStringProperty RegNumber;
    private final SimpleStringProperty bookingType;
    private final SimpleStringProperty futureBookingDate;

    public booking(String RegNumber2, String bookingType1, String futureBookingDate1) {

        this.RegNumber = new SimpleStringProperty(RegNumber2);
        this.bookingType = new SimpleStringProperty(bookingType1);
        this.futureBookingDate = new SimpleStringProperty(futureBookingDate1);

    }

    public String getRegBooking() {
        return RegNumber.get();
    }

    public String getBType() {
        return bookingType.get();
    }

    public String getFBDate() {
        return futureBookingDate.get();
    }

}