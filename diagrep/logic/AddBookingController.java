/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;

/**
 * FXML Controller class
 *
 * @author Sajjad
 */
public class AddBookingController implements Initializable {

    @FXML
    private SplitMenuButton listCustomerID;
    @FXML
    private SplitMenuButton listVehicleID;
    @FXML
    private Button confirmAddButton;
    @FXML
    private Button resetbooking;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmAddBooking(ActionEvent event) {
    }

    @FXML
    private void ClearBookingDetails(ActionEvent event) {
    }
    
}
