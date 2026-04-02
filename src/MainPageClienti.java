

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

public class MainPageClienti implements Initializable {
    @FXML
    private TableView<Client> tableView;
    @FXML
    private TableColumn<Client, String> nume;
    @FXML
    private TableColumn<Client, String> prenume;
    @FXML
    private TableColumn<Client, String> idnp;
    @FXML
    private TableColumn<Client, String> adresa;
    @FXML
    private TableColumn<Client, String> nrTelefon;
    @FXML
    private Button deleteSelectedRowBtn;
    @FXML
    private Button addRowBtn;
    @FXML
    private Button showEditRowBtn;

    private ArrayList<Client> clienti = new ArrayList<>();
    private ObservableList<Client> clientiList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadDataFromFileClienti("src\\DataBase\\Clienti.txt");
        clientiList = FXCollections.observableArrayList(clienti);
        try {
            nume.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNume()));
            prenume.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrenume()));
            idnp.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIdnp()));
            adresa.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresa()));
            nrTelefon.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNrTelefon()));
            tableView.setItems(clientiList);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void loadDataFromFileClienti(String filePath) {
        try (Scanner read = new Scanner(new FileReader(new File(filePath)))) {
            while (read.hasNext()) {
                clienti.add(new Client(read.next(), read.next(), read.next(), read.next(), read.next()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteSelectedRowBtn() {
        Client selectedClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedClient == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete.");
            alert.showAndWait();
            return;
        }

        if (showAlert()) {
            clientiList.remove(selectedClient);
            clienti.remove(selectedClient);
            updateFile("src\\DataBase\\Clienti.txt");
        }
    }

    private void updateFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Client client : clienti) {
                writer.write(client.getNume() + " " + client.getPrenume() + " " +
                             client.getIdnp() + " " + client.getAdresa() + " " + client.getNrTelefon());
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

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(numeInput, prenumeInput, idnpInput, adresaInput, nrTelefonInput);

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

                Client newClient = new Client(nume, prenume, idnp, adresa, nrTelefon);
                clienti.add(newClient);
                clientiList.add(newClient);

                updateFile("src\\DataBase\\Clienti.txt");
            }
        });

        alert.showAndWait();
    }

    public void ShowEditRowBtn() {
        Client selectedClient = tableView.getSelectionModel().getSelectedItem();

        if (selectedClient == null) {
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

        TextField numeInput = new TextField(selectedClient.getNume());
        TextField prenumeInput = new TextField(selectedClient.getPrenume());
        TextField idnpInput = new TextField(selectedClient.getIdnp());
        TextField adresaInput = new TextField(selectedClient.getAdresa());
        TextField nrTelefonInput = new TextField(selectedClient.getNrTelefon());

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(numeInput, prenumeInput, idnpInput, adresaInput, nrTelefonInput);

        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0, 0);
        alert.getDialogPane().setContent(gridPane);

        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == ButtonType.OK) {
                selectedClient.setNume(numeInput.getText());
                selectedClient.setPrenume(prenumeInput.getText());
                selectedClient.setIdnp(idnpInput.getText());
                selectedClient.setAdresa(adresaInput.getText());
                selectedClient.setNrTelefon(nrTelefonInput.getText());
                tableView.refresh();
                updateFile("src\\DataBase\\Clienti.txt");
            }
        });

        alert.showAndWait();
    }
}
