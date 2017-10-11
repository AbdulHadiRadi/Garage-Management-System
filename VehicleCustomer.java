package vehicles.logic;

/**
 * Created by abdul on 3/14/2017.
 */
public class VehicleCustomer {
//Instance Variables declaration    
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    //Constructor
    public VehicleCustomer(){

    }
    //Constructor
    public VehicleCustomer(String firstName, String lastName, String phone, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
     //GETTER AND SETTER METHOD OF INSTANCE VARIABLES
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
