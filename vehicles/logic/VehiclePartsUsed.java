package vehicles.logic;

/**
 * Created by abdul on 3/14/2017.
 */
public class VehiclePartsUsed {

//Instance Variables declaration
    private String usedParts;
    private String date;
     //Constructor
    public VehiclePartsUsed(){

    }
     //Constructor
    public VehiclePartsUsed(String parts, String date){

        this.usedParts = parts;
        this.date = date;

    }
    
     //GETTER AND SETTER METHOD OF INSTANCE VARIABLES
    public String getUsedParts() {

        return usedParts;

    }

    public void setUsedParts(String usedParts) {

        this.usedParts = usedParts;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
