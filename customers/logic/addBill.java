package customers.logic;

import common.logic.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * Created by Fahim on 19/02/2017.
 */
public class addBill {


    @FXML private TextField billBookingBox;
    @FXML private TextField partUsedBox;
    @FXML private TextField spcBookingBox;
    private int selectedType1 = -1;

    DBConnection conn = DBConnection.getInstance();
    Connection db = conn.getConnection();

    @FXML
    public void addBill(ActionEvent event)
    {
        try {
            if (selectedType1 != -1) {

                String stmt = "INSERT INTO Bill (PartUsedID, bookingID, spcBookingID, status) VALUES ('"
                        + partUsedBox.getText() + "', '"
                        + billBookingBox.getText() + "', '"
                        + spcBookingBox.getText() + "', '"
                        + selectedType1 + "')";

                System.out.println(stmt);
                update(stmt);
                ((Node) (event.getSource())).getScene().getWindow().hide();


            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please select type");
                alert.showAndWait();
            }
        } catch (NullPointerException e)
        {
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
    public void selectPaid() {selectedType1 = 1;}

    @FXML
    public void selectNotPaid()
    {
        selectedType1 = 0;
    }

}
