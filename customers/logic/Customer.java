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
public class Customer {
    private IntegerProperty id;
    private StringProperty fName;
    private StringProperty sName;
    private StringProperty add;
    private StringProperty postcode;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty type;


    public Customer(ResultSet rs)
    {
        try {
            this.id = new SimpleIntegerProperty(rs.getInt("CustomerID"));
            this.fName = new SimpleStringProperty(rs.getString("FirstName"));
            this.sName = new SimpleStringProperty(rs.getString("Lastname"));
            this.postcode = new SimpleStringProperty(rs.getString("PostCode"));
            this.add = new SimpleStringProperty(rs.getString("Address"));
            this.phone = new SimpleStringProperty(rs.getString("Phone"));
            this.email = new SimpleStringProperty(rs.getString("Email"));
            if (rs.getInt("Type") == 1)
            {
                this.type = new SimpleStringProperty("Business");
            }
            else
            {
                this.type = new SimpleStringProperty("Private");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getsName() {
        return sName.get();
    }

    public StringProperty sNameProperty() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName.set(sName);
    }

    public String getAdd() {
        return add.get();
    }

    public StringProperty addProperty() {
        return add;
    }

    public void setAdd(String add) {
        this.add.set(add);
    }

    public String getPostcode() {
        return postcode.get();
    }

    public StringProperty postcodeProperty() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode.set(postcode);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {this.email.set(email);}

    public String gettype() {return type.get();}

    public StringProperty typeProperty() {return type;}

    public void setType(String Type) {this.type.set(Type);}
}
