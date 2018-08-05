package stockentry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterProductsController implements Initializable {
    @FXML    private TextField id;    @FXML    private TextField name;    @FXML    private TextField price;  @FXML    private TextField quantity;
    @FXML
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    public void onClick(ActionEvent A){
        try{
        if(id.getText().equals("") || name.getText().equals("") || price.getText().equals("") || quantity.getText().equals("")){
        Alert A1=new Alert(Alert.AlertType.WARNING,"Fields cannot be left blank");
        A1.setTitle("Register Product");
        A1.setHeaderText("Registration Result");
        A1.showAndWait();
        id.requestFocus();
        return;
        }
        MainController.st=MainController.Con.createStatement();
        MainController.P=MainController.Con.prepareStatement("insert into product values (?,?,?,?)");  
        MainController.P.setInt(1,Integer.parseInt(id.getText()));
        MainController.P.setString(2,name.getText());
        MainController.P.setDouble(3,Double.parseDouble(price.getText()));
        MainController.P.setInt(4,Integer.parseInt(quantity.getText()));
        MainController.P.executeUpdate();
        Alert A1=new Alert(Alert.AlertType.INFORMATION,"Product Registration Successful");
        A1.setTitle("Register Product");
        A1.setHeaderText("Registration Result");
        A1.showAndWait();
        id.setText(""); name.setText(""); price.setText(""); id.requestFocus(); quantity.setText("");
    }catch (Exception E){
        Alert A1=new Alert(Alert.AlertType.ERROR,"Product Registration Unsuccessful.   "+E);
        A1.setTitle("Register Product");
        A1.setHeaderText("Invalid Data");
        A1.showAndWait();
    }
 
}
     
        public void cancelReg(ActionEvent A) throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("Menu.fxml"));
            rootPane.getChildren().setAll(pane);
    }
}
