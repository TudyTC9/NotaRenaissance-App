import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainPageControllerSa{
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button filialeBtn;
    @FXML
    private Button contracteBtn;
    @FXML
    private Button accountBtn;
    @FXML
    private Button clientiBtn;
    @FXML
    private Button angajatiBtn;

    

    public void FilialeBtn(ActionEvent event){
        loadPage("Interface\\MainPageFiliale.fxml");

    }
    public void ContracteBtn(ActionEvent event){
        loadPage("Interface\\MainPageContracte.fxml");
    }
    public void ClientiBtn(ActionEvent event){
        loadPage("Interface\\MainPageClienti.fxml");
    }
    public void AngajatiBtn(ActionEvent event){
        loadPage("Interface\\MainPageAngajati.fxml");
    }
    public void AccountBtn(ActionEvent event){
        
    }
    private void loadPage(String fxmlFile) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource(fxmlFile));
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(page);
            AnchorPane.setTopAnchor(page, 0.0);
            AnchorPane.setBottomAnchor(page, 0.0);
            AnchorPane.setLeftAnchor(page, 0.0);
            AnchorPane.setRightAnchor(page, 0.0);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
