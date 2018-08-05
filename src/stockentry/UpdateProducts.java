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
//Updateproducts Controller
public class UpdateProducts implements Initializable {
    @FXML TextField id; @FXML TextField quantity;
    @FXML 
    AnchorPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    public void onClick(ActionEvent A){
        
                int addquant=0;
        try{
        if(id.getText().equals("") || quantity.getText().equals("")){
        Alert A1=new Alert(Alert.AlertType.WARNING,"Fields cannot be left blank");
        A1.setTitle("Update Product");
        A1.setHeaderText("Update Result");
        A1.showAndWait();
        id.requestFocus();
        return;
        }
        
           
        MainController.st=MainController.Con.createStatement();
        MainController.P=MainController.Con.prepareStatement("select quantity from product where id=(?)");
        MainController.P.setInt(1,Integer.parseInt(id.getText()));
        MainController.Rs=MainController.P.executeQuery();
        if(MainController.Rs.next()){
            addquant=Integer.parseInt(quantity.getText())+MainController.Rs.getInt(1);
            }
        else{
            Alert A1=new Alert(Alert.AlertType.ERROR,"No data found under this Product ID");
        A1.setTitle("Update Product");
        A1.setHeaderText("Invalid Data");
        A1.showAndWait();
        return;
        }
        
        MainController.st=MainController.Con.createStatement();
        MainController.P=MainController.Con.prepareStatement("update product set quantity=(?) where id=(?)");  
        MainController.P.setInt(1,addquant);
        MainController.P.setInt(2,Integer.parseInt(id.getText()));
        MainController.P.executeUpdate();
        Alert A1=new Alert(Alert.AlertType.INFORMATION,"Successfully Updated");
        A1.setTitle("Update Product");
        A1.setHeaderText("Update Result");
        A1.showAndWait();
        id.setText(""); quantity.setText("");
    }catch (Exception E){
        Alert A1=new Alert(Alert.AlertType.ERROR,"Product Update Unsuccessful.");
        A1.setTitle("Update Product");
        A1.setHeaderText("Invalid Data");
        A1.showAndWait();
    }
    }
    
        public void cancelUpd(ActionEvent A) throws IOException {
            AnchorPane pane=FXMLLoader.load(getClass().getResource("Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        }
}
