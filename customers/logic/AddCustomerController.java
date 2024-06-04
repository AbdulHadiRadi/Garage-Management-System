package customers.logic;

import common.logic.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Created by Fahim on 19/02/2017.
 */
public class AddCustomerController {

    //get all the textfields
    @FXML private TextField firstName;
    @FXML private TextField surname;
    @FXML private TextField address;
    @FXML private TextField postcode;
    @FXML private TextField phone;
    @FXML private TextField email;
    private int selectedType = -1;

    //connect to the database
    DBConnection conn = DBConnection.getInstance();
    Connection db = conn.getConnection();

    //insert the information added to the database
    @FXML
    public void add(ActionEvent event)
    {
        try {
            if  (validation())
            {

                String stmt = "INSERT INTO Customer (Firstname, Lastname, Address, Postcode, Phone, Email, Type) VALUES ('"
                        + firstName.getText() + "', '"
                        + surname.getText() + "', '"
                        + address.getText() + "', '"
                        + postcode.getText() + "', '"
                        + phone.getText() + "', '"
                        + email.getText() +"',"
                        + selectedType + ")";

                 update(stmt);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        }finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                     conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    //validate data to make sure correct data is filled in
    boolean validation() {
        try {
            if (firstName.getText() == null) {
                JOptionPane.showMessageDialog(null, "ENTER a First Name");
                return false;
            }
            if (surname.getText() == null) {
                JOptionPane.showMessageDialog(null, "Enter a Surname");
                return false;
            }
            if (address == null) {
                JOptionPane.showMessageDialog(null, "Enter an address");
                return false;
            }
            if (phone == null) {
                JOptionPane.showMessageDialog(null, "Enter an phone number");
                return false;
            }
            if (email == null) {
                JOptionPane.showMessageDialog(null, "Enter an email");
                return false;
            }
            if (postcode == null) {
                JOptionPane.showMessageDialog(null, "add a postcode");
                return false;
            }
            if(phone!=null)
            {
                Long.parseLong(phone.getText());
            }
            if (!firstName.getText().matches("^[a-zA-Z\\s]*$"))
            {
                JOptionPane.showMessageDialog(null, "You entered a number in first name");
                return false;
            }
            if (!surname.getText().matches("^[a-zA-Z\\s]*$"))
            {
                JOptionPane.showMessageDialog(null, "You entered a number in surname name");
                return false;
            }
         }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Please check you have entered data in all fields and of the right type");
            return false;
        }
        return true;
    }

    //update the changes to database
    public void update(String sql) {
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (NullPointerException ne) {
        }finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }


    @FXML
    public void cancel(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }


    @FXML
    public void selectBusiness()
    {
        selectedType = 1;
    }

    @FXML
    public void selectPrivate()
    {
        selectedType = 0;
    }

}
