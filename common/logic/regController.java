package common.logic;

/**
 * Created by abdul on 3/18/2017.
 */
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class regController implements Initializable{

        @FXML
        private TextField firstName;

        @FXML
        private TextField surname;

        @FXML
        private TextField userName;

        @FXML
        private PasswordField newPassword;

        @FXML
        private PasswordField confirmPassword;

        @FXML
        private Button cancelButton;

        @FXML
        private Button registerButton;

        @FXML
        private Button clearButton;

        @FXML
        private Label label;

        @FXML
        public void cancelButtonAction(ActionEvent event) throws Exception{
            Stage main = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            main.close();
        }

        @FXML
        void clearButtonAction(ActionEvent event) throws Exception{
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/register.fxml"));
            Scene scene = new Scene(parent);
            Stage mainWindow; //Here is the magic. We get the reference to main Stage.
            mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            mainWindow.setScene(scene);
        }

        @FXML
        void registerButtonAction(ActionEvent event) throws Exception{
           try{
               if( userName.getText().isEmpty() || firstName.getText().isEmpty() || surname.getText().isEmpty() || newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Fields");
                alert.setContentText("You had to fill all the fields. Some fields are still empty!");
                alert.showAndWait();
            }
               else if(userName.getText().length()!=5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("The ID had to be exactly 5digit numbers!");
                alert.showAndWait();     
               }   
               else if((userName.getText().length()==5) && (!uniqueIDCheck(Integer.parseInt(userName.getText())))){
                   
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("This ID already exist in the system!");
                alert.showAndWait();
               
               }
               else{

                  if(newPassword.getText().equals(confirmPassword.getText())){
                    submitDetail();
                    Stage main = (Stage)  ((Node)event.getSource()).getScene().getWindow();
                    main.close();
               }
               else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password");
                alert.setContentText("Both Passwords does not match!");
                alert.showAndWait();
                   }
               }
           }
           catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("The ID had to be only numbers!");
                alert.showAndWait();
           }
    }


    public boolean uniqueIDCheck(int id){
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        boolean isFound = true;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = null;
            rs = statement.executeQuery("SELECT *From 'User'");
            while (rs.next()){
                if(rs.getInt("username") == id ){
                   isFound = false;
                }
            }
                statement.close();
                conn.close();
                return isFound;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        return isFound;
    }

    public void submitDetail(){
         DBConnection db =  DBConnection.getInstance();
         Connection conn = db.getConnection();
         Statement statement;
      try {
             statement = conn.createStatement();
             statement.setQueryTimeout(10);
             statement.executeUpdate("insert into 'User' values ('"+userName.getText()+"','User','"+firstName.getText()+"','"+surname.getText()+"','"+confirmPassword.getText()+"')");
             statement.close();
             conn.close();
           } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                } finally {
                        if (db.getConnection() != null) {
                                try {
                                                db.getConnection().close();
                                } catch (SQLException ex) {
                                                System.err.println(ex.getMessage());
                                }
                        }
                }
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerButton.disableProperty().bind(firstName.textProperty().isEmpty().or(surname.textProperty().isEmpty()).or(userName.textProperty().isEmpty()).or(newPassword.textProperty().isEmpty()).or(confirmPassword.textProperty().isEmpty()));
    }
}



