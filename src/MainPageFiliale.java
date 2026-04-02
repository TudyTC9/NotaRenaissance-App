import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainPageFiliale implements Initializable{
    @FXML
    private TableView<Filiala> tableView;
    @FXML
    private TableColumn<Filiala, String> adresa;
    @FXML
    private TableColumn<Filiala, String> nrInregistrare;
    @FXML
    private TableColumn<Filiala, String> nrTelefon;
    @FXML
    private TableColumn<Filiala, String> nume;
    @FXML
    private Button deleteSelectedRowBtn;
    @FXML
    private Button addRowBtn;

    private ArrayList<Filiala> filiale = new ArrayList<>();
    private ObservableList<Filiala> filialeList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        loadDataFromFileFiliale("src\\DataBase\\Filiale.txt");
        filialeList = FXCollections.observableArrayList(filiale);
        try {
        nume.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNume()));
        nrInregistrare.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNrInregistrare()));
        nrTelefon.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNrTelefon()));
        adresa.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresa()));
        tableView.setItems(filialeList);
        } catch (Exception e) {
        }
    }
    public void loadDataFromFileFiliale(String filePath) {
        try (Scanner read = new Scanner(new FileReader(new File(filePath)))) {
            while (read.hasNext()) {
                filiale.add(new Filiala(read.next(), read.next(), read.next(), read.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DeleteSelectedRowBtn() {
        if(showAlert()){
            Filiala selectedFiliala = tableView.getSelectionModel().getSelectedItem();
            if (selectedFiliala != null) {
                filialeList.remove(selectedFiliala);
                filiale.remove(selectedFiliala);
                updateFile("src\\DataBase\\Filiale.txt");
            }
        }
        
    }
    private void updateFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Filiala filiala : filiale) {
                writer.write(filiala.getNume() + " " + filiala.getNrInregistrare() + " " +
                             filiala.getNrTelefon() + " " + filiala.getAdresa());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean showAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm DELETE");
        alert.setContentText("You want to delete that row?");
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        if (alert.showAndWait().orElse(buttonTypeCancel) == buttonTypeOK) {
            return true;
        } else {
            return false;
        }
    }
    public void AddRowBtn() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Add");
        alert.setHeaderText("Enter data:");

        TextField numeInput = new TextField();
        numeInput.setPromptText("Nume..");
        TextField nrInregistrareInput = new TextField();
        nrInregistrareInput.setPromptText("Nr Inregistrare..");
        TextField nrTelefonInput = new TextField();
        nrTelefonInput.setPromptText("Nr Telefon..");
        TextField adresaInput = new TextField();
        adresaInput.setPromptText("Adresa..");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(numeInput, nrInregistrareInput, nrTelefonInput, adresaInput);

        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0, 0);
        alert.getDialogPane().setContent(gridPane);

        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == ButtonType.OK) {
                String nume = numeInput.getText();
                String nrInregistrare = nrInregistrareInput.getText();
                String nrTelefon = nrTelefonInput.getText();
                String adresa = adresaInput.getText();

                Filiala newFiliala = new Filiala(nume, nrInregistrare, nrTelefon, adresa);
                filiale.add(newFiliala);
                filialeList.add(newFiliala);

                updateFile("src\\DataBase\\Filiale.txt");
            }
        });

        alert.showAndWait();
    }
}
