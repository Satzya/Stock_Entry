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

public class SellProductsController implements Initializable {
    @FXML TextField id; @FXML TextField name; @FXML TextField price; @FXML TextField avquant; @FXML TextField entquant;  
    @FXML
    AnchorPane rootPane;

    public SellProductsController() {
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setEditable(false); price.setEditable(false); avquant.setEditable(false);
    }    
    public void onClick(ActionEvent A){
        try{
            if(id.getText().equals("")){
                Alert A1=new Alert(Alert.AlertType.WARNING,"Provide Specific Product ID ");
        A1.setTitle("Sell Product");
        A1.setHeaderText("Sales Result");
        A1.showAndWait();
        name.setText(""); price.setText(""); avquant.setText(""); entquant.setText("");
        id.requestFocus();
        return;
            }
        MainController.st=MainController.Con.createStatement();
        MainController.P=MainController.Con.prepareStatement("select name,price,quantity from product where id=(?)");
        MainController.P.setInt(1,Integer.parseInt(id.getText()));
        MainController.Rs=MainController.P.executeQuery();
        if(MainController.Rs.next()){
            name.setText(MainController.Rs.getString(1));
            price.setText(Double.toString(MainController.Rs.getDouble(2)));
            avquant.setText(Integer.toString(MainController.Rs.getInt(3)));
            }
        else{
            Alert A1=new Alert(Alert.AlertType.ERROR, "No product registered under this Product ID "+id.getText());
            A1.setTitle("Sell Product");
            A1.setHeaderText("Sales Result");
            A1.showAndWait();
            return;
        }
        }
        catch(Exception E){
        Alert A1=new Alert(Alert.AlertType.ERROR,"Invalid "+E);
        A1.setTitle("Sell Product");
        A1.setHeaderText("Sales Result");
        A1.showAndWait();
        }
    }
    
    public void onClickBut(ActionEvent A){
        try{
            if(avquant.getText().equals("0")){
            Alert A1=new Alert(Alert.AlertType.WARNING, "Stock empty");
            A1.setTitle("Sell Product");
            A1.setHeaderText("Sales Result");
            A1.showAndWait();
            id.requestFocus();
            return;
            }
            else if(name.getText().equals("") || price.getText().equals("") || avquant.getText().equals("") || entquant.getText().equals("")){
            Alert A1=new Alert(Alert.AlertType.WARNING, "Values not specified");
            A1.setTitle("Sell Product");
            A1.setHeaderText("Sales Result");
            A1.showAndWait();
            id.requestFocus();
            return;
        }
        else if(Integer.parseInt(entquant.getText())>Integer.parseInt(avquant.getText())){
            Alert A1=new Alert(Alert.AlertType.WARNING, "Entered quantity is higher than the reserved quntaties");
            A1.setTitle("Sell Product");
            A1.setHeaderText("Sales Result");
            A1.showAndWait();
            entquant.requestFocus();
            return;
        }
        
            int a=0;
            a=Integer.parseInt(avquant.getText())-Integer.parseInt(entquant.getText());
        MainController.st=MainController.Con.createStatement();
        MainController.P=MainController.Con.prepareStatement("update product set quantity=(?) where id=(?)");
        MainController.P.setInt(1,a);
        MainController.P.setInt(2,Integer.parseInt(id.getText()));
        MainController.P.executeUpdate();
        Alert A1=new Alert(Alert.AlertType.INFORMATION,"Successfully sold out");
        A1.setTitle("Sell Product");
        A1.setHeaderText("Sales Result");
        A1.showAndWait();
            if(a<10){
            Alert A2=new Alert(Alert.AlertType.INFORMATION,"You have less than "+a+" products registered under Product ID: "+id.getText()+". Please restock items soon");
            A2.setTitle("Sell Product");
            A2.setHeaderText("Sales Result");
            A2.showAndWait();
            }
            id.setText("");name.setText("");price.setText("");avquant.setText("");entquant.setText("");
        }
        
        catch(Exception E){
        Alert A1=new Alert(Alert.AlertType.ERROR,"Invalid "+E);
        A1.setTitle("Sell Product");
        A1.setHeaderText("Sales Result");
        A1.showAndWait();
        id.setText("");name.setText("");price.setText("");avquant.setText("");entquant.setText("");
        }
    }
    
         public void cancelSel(ActionEvent A) throws IOException {
            AnchorPane pane=FXMLLoader.load(getClass().getResource("Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        }
}
    