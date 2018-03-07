package common.logic;

/**
 * Created by abdul on 3/18/2017.
 *Controller for registraion user interface vehicle module
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

        //Instance variables
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
        
        // registration cancel button action 
        @FXML
        public void cancelButtonAction(ActionEvent event) throws Exception{
            Stage main = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            main.close();
        }

        // registration fields clear button action 
        @FXML
        void clearButtonAction(ActionEvent event) throws Exception{
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/register.fxml"));
            Scene scene = new Scene(parent);
            Stage mainWindow; //getting the reference to main Stage.
            mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            mainWindow.setScene(scene);
        }
        
        // registration submit button action
        @FXML
        void registerButtonAction(ActionEvent event) throws Exception{
                
           try{
                   //empty fields error
               if( userName.getText().isEmpty() || firstName.getText().isEmpty() || surname.getText().isEmpty() || newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Fields");
                alert.setContentText("You had to fill all the fields. Some fields are still empty!");
                alert.showAndWait();
            }
                   //uerid error  
               else if(userName.getText().length()!=5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("The ID had to be exactly 5digit numbers!");
                alert.showAndWait();     
               }   
                   //existed userid error
               else if((userName.getText().length()==5) && (!uniqueIDCheck(Integer.parseInt(userName.getText())))){                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("This ID already exist in the system!");
                alert.showAndWait();
               
               }
               else{
                       //No error case for submitting
                  if(newPassword.getText().equals(confirmPassword.getText())){
                    submitDetail();
                    Stage main = (Stage)  ((Node)event.getSource()).getScene().getWindow();
                    main.close();
                  }     
                  else{
                          //Password errors
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Password");
                        alert.setContentText("Both Passwords does not match!");
                        alert.showAndWait();
                   }
               }
           }
           catch(NumberFormatException e){
                   //Non numeric userid error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("The ID had to be only numbers!");
                alert.showAndWait();
           }
    }

// Checking unique id for the user. returned true if userid already existed. Otherwise false.
    public boolean uniqueIDCheck(int id){
            //Using DBConnection object type for the connection with the database.
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
        // Saving detail to the database
    public void submitDetail(){
              //Using DBConnection object type for the connection with the database.
         DBConnection db =  DBConnection.getInstance();
         Connection conn = db.getConnection();
         Statement statement;
      try {
             statement = conn.createStatement();
             statement.setQueryTimeout(10);
              // insert query for submiting user details
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

        // initial loading fields for the fxml
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerButton.disableProperty().bind(firstName.textProperty().isEmpty().or(surname.textProperty().isEmpty()).or(userName.textProperty().isEmpty()).or(newPassword.textProperty().isEmpty()).or(confirmPassword.textProperty().isEmpty()));
    }
}



