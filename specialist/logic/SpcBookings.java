    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

/**
 *
 * @author Yousuf
 */
public class SpcBookings {
    
    private int spcBookingId;
    private  int spcId;
    private Integer  partId;
    private String vehicleId;
    private String deliveryDate;
    private String returnDate;
    private int fBookingId;
    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public SpcBookings(int spcBookingId, int spcId, Integer partId, String vehicleId, String deliveryDate, String returnDate, int fBookingId, double cost) {
        this.spcBookingId = spcBookingId;
        this.spcId = spcId;
        this.partId = partId;
        this.vehicleId = vehicleId;
        this.deliveryDate = deliveryDate;
        this.returnDate = returnDate;
        this.fBookingId = fBookingId;
        this.cost = cost;
    }

    public int getfBookingId() {
        return fBookingId;
    }

    public void setfBookingId(int fBookingId) {
        this.fBookingId = fBookingId;
    }


    public int getSpcBookingId() {
        return spcBookingId;
    }

    public void setSpcBookingId(int spcBookingId) {
        this.spcBookingId = spcBookingId;
    }

    public int getSpcId() {
        return spcId;
    }

    public void setSpcId(int spcId) {
        this.spcId = spcId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
   
}
