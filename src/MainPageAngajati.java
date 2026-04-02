
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

public class MainPageAngajati implements Initializable {

    @FXML
    private TableView<Angajat> tableView;
    @FXML
    private TableColumn<Angajat, String> nume;
    @FXML
    private TableColumn<Angajat, String> prenume;
    @FXML
    private TableColumn<Angajat, String> idnp;
    @FXML
    private TableColumn<Angajat, String> adresa;
    @FXML
    private TableColumn<Angajat, String> telefon;
    @FXML
    private TableColumn<Angajat, String> servicii;
    @FXML
    private Button deleteSelectedRowBtn;
    @FXML
    private Button addRowBtn;
    @FXML
    private Button showEditRowBtn;

    private ArrayList<Angajat> angajati = new ArrayList<>();
    private ObservableList<Angajat> angajatiList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            loadDataFromFileAngajati("src\\DataBase\\Angajati.txt");
            angajatiList = FXCollections.observableArrayList(angajati);
            nume.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNume()));
            prenume.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrenume()));
            idnp.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIdnp()));
            adresa.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresa()));
            telefon.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNrTelefon()));
            servicii.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getServicii()));
            tableView.setItems(angajatiList);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void loadDataFromFileAngajati(String filePath) {
        try (Scanner read = new Scanner(new FileReader(new File(filePath)))) {
            while (read.hasNext()) {
                angajati.add(new Angajat(read.next(), read.next(), read.next(), read.next(), read.next(), read.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteSelectedRowBtn() {
        Angajat selectedAngajat = tableView.getSelectionModel().getSelectedItem();

        if (selectedAngajat == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
            return;
        }

        if (showAlert()) {
            angajatiList.remove(selectedAngajat);
            angajati.remove(selectedAngajat);
            updateFile("src\\DataBase\\Angajati.txt");
        }
    }

    private void updateFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Angajat angajat : angajati) {
                writer.write(angajat.getNume() + " " + angajat.getPrenume() + " " +
                        angajat.getIdnp() + " " + angajat.getAdresa() + " " +
                        angajat.getNrTelefon() + " " + angajat.getServicii());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean showAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm DELETE");
        alert.setContentText("You want to delete that row?");
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        return alert.showAndWait().orElse(buttonTypeCancel) == buttonTypeOK;
    }

    public void AddRowBtn() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Add");
        alert.setHeaderText("Enter data:");

        TextField numeInput = new TextField();
        numeInput.setPromptText("Nume..");
        TextField prenumeInput = new TextField();
        prenumeInput.setPromptText("Prenume..");
        TextField idnpInput = new TextField();
        idnpInput.setPromptText("IDNP..");
        TextField adresaInput = new TextField();
        adresaInput.setPromptText("Adresa..");
        TextField nrTelefonInput = new TextField();
        nrTelefonInput.setPromptText("Nr Telefon..");
        TextField serviciiInput = new TextField();
        serviciiInput.setPromptText("Servicii..");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(numeInput, prenumeInput, idnpInput, adresaInput, nrTelefonInput, serviciiInput);

        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0, 0);
        alert.getDialogPane().setContent(gridPane);

        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == ButtonType.OK) {
                String nume = numeInput.getText();
                String prenume = prenumeInput.getText();
                String idnp = idnpInput.getText();
                String adresa = adresaInput.getText();
                String nrTelefon = nrTelefonInput.getText();
                String servicii = serviciiInput.getText();

                Angajat newAngajat = new Angajat(nume, prenume, idnp, adresa, nrTelefon, servicii);
                angajati.add(newAngajat);
                angajatiList.add(newAngajat);

                updateFile("src\\DataBase\\Angajati.txt");
            }
        });

        alert.showAndWait();
    }

    public void ShowEditRowBtn() {
        Angajat selectedAngajat = tableView.getSelectionModel().getSelectedItem();
        if (selectedAngajat == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to edit.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Edit");
        alert.setHeaderText("Enter data:");

        TextField numeInput = new TextField(selectedAngajat.getNume());
        TextField prenumeInput = new TextField(selectedAngajat.getPrenume());
        TextField idnpInput = new TextField(selectedAngajat.getIdnp());
        TextField adresaInput = new TextField(selectedAngajat.getAdresa());
        TextField nrTelefonInput = new TextField(selectedAngajat.getNrTelefon());
        TextField serviciiInput = new TextField(selectedAngajat.getServicii());

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(numeInput, prenumeInput, idnpInput, adresaInput, nrTelefonInput, serviciiInput);

        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0, 0);
        alert.getDialogPane().setContent(gridPane);

        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == ButtonType.OK) {
                selectedAngajat.setNume(numeInput.getText());
                selectedAngajat.setPrenume(prenumeInput.getText());
                selectedAngajat.setIdnp(idnpInput.getText());
                selectedAngajat.setAdresa(adresaInput.getText());
                selectedAngajat.setNrTelefon(nrTelefonInput.getText());
                selectedAngajat.setServicii(serviciiInput.getText());
                tableView.refresh();
                updateFile("src\\DataBase\\Angajati.txt");
            }
        });

        alert.showAndWait();
    }
}