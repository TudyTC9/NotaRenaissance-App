import java.io.FileWriter;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountPageController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField nume;
    @FXML
    private TextField prenume;
    @FXML
    private TextField nrTelefon;
    @FXML
    private TextField adresa;
    @FXML
    private Button createAccountBtn;
    @FXML
    private Button logInBtn;

    

    public void CreateAccountBtn(ActionEvent event){
        try {
            FileWriter fout = new FileWriter(new File("src\\DataBase\\Users.txt"), true);
            boolean accountCanBeCreated = true;
            for(int i=0;i<App.users.size();i++){
                if(App.users.get(i).getUsername().equals(username.getText()) || App.users.get(i).getNrTelefon().equals(nrTelefon.getText())){
                    accountCanBeCreated = false;
                }
            }
            if(accountCanBeCreated){
                fout.write("user " + username.getText() + " " + password.getText() + " " + nume.getText() + " " + prenume.getText() + " " + nrTelefon.getText() + " " + adresa.getText() + "\n");
                App.users.add(new User("user", username.getText(), password.getText(), nume.getText(), prenume.getText(), nrTelefon.getText(), adresa.getText()));
                LogInBtn(event);
            }
            else System.out.println("Invalid username or phone");
            fout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void LogInBtn(ActionEvent event){
        try {
            Parent login = FXMLLoader.load(getClass().getResource("Interface\\LogInPage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(login));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
