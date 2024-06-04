package customers.logic;

import common.logic.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 * Created by Fahim on 19/02/2017.
 */



public class EditCustomerController implements Initializable{

    //getting all the textfields
    @FXML private TextField firstName;
    @FXML private TextField surname;
    @FXML private TextField address;
    @FXML private TextField postcode;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private RadioButton b;
    @FXML private RadioButton p;
    private int selectedType = -1;
    DBConnection conn = DBConnection.getInstance();
    Connection db = conn.getConnection();

    //get the selected customer 
    private Customer selected = CustomerController.getSelected();

    //setting the text for each column in the right textfield
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        firstName.setText(selected.getfName());
        surname.setText(selected.getsName());
        address.setText(selected.getAdd());
        postcode.setText(selected.getPostcode());
        phone.setText(selected.getPhone());
        email.setText(selected.getEmail());
        if (selected.gettype().equals("Business"))
        {
            b.setSelected(true);
            selectedType = 1;
        }
        else
        {
            p.setSelected(true);
            selectedType = 0;
        }

    }

    //updating the datatbase with the edited information
    @FXML
    public void edit(ActionEvent event)
    {
        try {
            if (validation()) {
                //SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "UPDATE Customer SET Firstname = '" + firstName.getText() + "', Lastname = '" + surname.getText() + "', Address = '" + address.getText() + "', PostCode = '" + postcode.getText() + "', Phone = '" + phone.getText() +"', Type = " + selectedType + ", Email ='" + email.getText() +"' WHERE CustomerID = " + selected.getId();
                update(stmt);
                ((Node) event.getSource()).getScene().getWindow().hide();
        }
        } 
        catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
        finally {
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
    
    //check if the edited information is correct
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

    public void update(String sql) {
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (NullPointerException ne) {
        }
        finally {
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
