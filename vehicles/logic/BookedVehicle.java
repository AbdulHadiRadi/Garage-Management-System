package vehicles.logic;
import common.logic.DBConnection;
import java.text.DecimalFormat;
/**
 * Created by abdul on 3/14/2017.
 */
public class BookedVehicle {
    //Instance variables Declaration
    private String booking;
    private String totalCost;
    private String type;

    //Constructor
    public BookedVehicle(){}
    //Constructor
    public BookedVehicle( String booking, String type, String totalCost){
        this.booking = booking;
        this.totalCost = totalCost;
        this.type = type;
    }
    
    //Getter And Setter Methods of the Instance Variables
    
    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

}
