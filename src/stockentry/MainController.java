package stockentry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;

public class MainController extends Application{
    static String uid;
    static Connection Con=null;
    static Statement st=null;
    static PreparedStatement P=null; 
    static ResultSet Rs=null;
    public static void main(String[] args) {
        launch(args);
    }

        @Override
        public void start(Stage primaryStage) throws Exception {
            
            try{ 
    Alert A1=new Alert(Alert.AlertType.INFORMATION);
    A1.setTitle("Database Connectivity");
    Class.forName("com.mysql.jdbc.Driver");
    Con=DriverManager.getConnection("jdbc:mysql://localhost/stocks","root","root");
    A1.setHeaderText("Connection Prompt Result");
//    A1.setContentText("Connection Successful");
//    A1.showAndWait();
    }
    catch(Exception E){
        Alert A1=new Alert(Alert.AlertType.ERROR);
    A1.setContentText("Connection cannot be established "+E);
    A1.setHeaderText("Connection Prompt Result");
    A1.showAndWait();
    System.exit(0);
    }

//          Start of the stage    
            Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene=new Scene(root);
            primaryStage.setTitle("Smart Stock Maintenance");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
//            End of the stage
    
        }    
    
}
