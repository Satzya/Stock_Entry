package stockentry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginControl {
    @FXML
    private TextField T;
    @FXML
    private PasswordField Ps;
    @FXML
    AnchorPane rootPane;
    public void onClick(ActionEvent A){
        Alert A2=new Alert(Alert.AlertType.WARNING);
          try{
        if(T.getText().equals("") || Ps.getText().equals("")){
            A2.setTitle("Authentication");
            A2.setHeaderText("Fields Blank");
            A2.setContentText("Blank not allowed");
            A2.showAndWait();
            T.requestFocus();
            return;
        }
              
            
        MainController.st=MainController.Con.createStatement();
        MainController.P=MainController.Con.prepareStatement("select * from login where id=(?) and pswd=(?)");  
        MainController.P.setString(1,T.getText());
        MainController.P.setString(2,Ps.getText());
          MainController.Rs=MainController.P.executeQuery();
        if(MainController.Rs.next()==true){
            MainController.uid=T.getText();
            Alert A1=new Alert(Alert.AlertType.INFORMATION,"Login Successful");
            A1.setTitle("Authentication");
            A1.setHeaderText("User Login");
            A1.showAndWait();
            AnchorPane pane=FXMLLoader.load(getClass().getResource("Menu.fxml"));
            rootPane.getChildren().setAll(pane);
          }
        else{
            Alert A1=new Alert(Alert.AlertType.ERROR,"Login Failed");
            A1.setTitle("Authentication");
            A1.setHeaderText("User Login");
            A1.showAndWait();
        }}
        catch(Exception E){
            Alert A1=new Alert(Alert.AlertType.ERROR,"Invalid data "+E);
            A1.setTitle("Authentication");
            A1.setHeaderText("User Login");
            A1.showAndWait();
        } }
    
    public void cancelLogin(ActionEvent A){
        System.exit(1);
    }
}
