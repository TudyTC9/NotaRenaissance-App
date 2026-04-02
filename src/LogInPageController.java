import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInPageController {
    @FXML
    private TextField logIn;
    @FXML
    private PasswordField password;
    @FXML
    private Button logInBtn;
    @FXML
    private Button forgetPasswordBtn;
    @FXML
    private Button createAccountBtn;

    Stage stage = App.stage;

    public void LogInBtn(ActionEvent event) throws IOException{
        try {
            for(int i=0;i<App.users.size();i++){
                if(App.users.get(i).getUsername().equals(logIn.getText()) && App.users.get(i).getPassword().equals(password.getText())){
                    switch (App.users.get(i).getStatus()) {
                        case "sa":{
                            Parent login = FXMLLoader.load(getClass().getResource("Interface\\MainPageSa.fxml"));
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(login));
                        } break;
                        case "user":{
                            Parent login = FXMLLoader.load(getClass().getResource("Interface\\MainPageUser.fxml"));
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(login));
                        } break;
                        case "empl":{
                            Parent login = FXMLLoader.load(getClass().getResource("Interface\\MainPageEmpl.fxml"));
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(login));
                        } break;
                    }
                    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void ForgetPasswordBtn(ActionEvent event) throws Exception{
        
    }
    public void CreateAccountBtn(ActionEvent event) throws Exception{
        try {
            Parent login = FXMLLoader.load(getClass().getResource("Interface\\CreateAccountPage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(login));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
