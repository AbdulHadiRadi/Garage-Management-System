package diagrep.logic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


 
/**
*
* @author sajjad
*/
public class runBooking extends Application{
    


  
   @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("../gui/Bookingpage.fxml"));
        Scene scene = new Scene(root, 1214, 959);
        stage.setTitle("Welcome to00 GM-SIS");
        stage.setScene(scene);
        stage.show();
    } 
    public static void main(String[]args){
    Application.launch(); 
    }
}