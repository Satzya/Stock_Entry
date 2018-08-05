package stockentry;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MenuController implements Initializable {
    @FXML
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void registerP(ActionEvent A) throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("RegisterProducts.fxml"));
            rootPane.getChildren().setAll(pane);
    }
    
    public void purchaseP(ActionEvent A) throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("UpdateProducts.fxml"));
            rootPane.getChildren().setAll(pane);
    }
    public void sellP(ActionEvent A) throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("SellProducts.fxml"));
            rootPane.getChildren().setAll(pane);
    }
    public void logOut(ActionEvent A) throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("Login.fxml"));
            rootPane.getChildren().setAll(pane);
    }
}
