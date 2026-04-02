

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class MainPageContracte implements Initializable{
    @FXML
    private TableView<Contract> tableView;
    @FXML
    private TableColumn<Contract, String> nrInregistrare;
    @FXML
    private TableColumn<Contract, String> client;
    @FXML
    private TableColumn<Contract, String> notar;
    @FXML
    private TableColumn<Contract, String> angajat;
    @FXML
    private TableColumn<Contract, String> data;
    @FXML
    private TableColumn<Contract, Double> valoare;
    @FXML
    private Button deleteSelectedRowBtn;
    @FXML
    private Button addRowBtn;

    private ArrayList<Contract> contracte = new ArrayList<>();
    private ObservableList<Contract> contracteList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        loadDataFromFileContracte("src\\DataBase\\Contracte.txt");
        contracteList = FXCollections.observableArrayList(contracte);
        try {
        nrInregistrare.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNrInregistrare()));
        client.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getClientNume() + " " + cellData.getValue().getClientPrenume()));
        notar.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNotarNume() + " " + cellData.getValue().getNotarPrenume()));
        angajat.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAngajatNume() + " " + cellData.getValue().getAngajatPrenume()));
        data.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataIntocmire() + "-" + cellData.getValue().getDataValabilitate()));
        valoare.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValoare()));

        tableView.setItems(contracteList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void loadDataFromFileContracte(String filePath) {
        try (Scanner read = new Scanner(new FileReader(new File(filePath)))) {
            while (read.hasNext()) {
                contracte.add(new Contract(read.next(), read.next(), read.next(), read.next(), read.next(), read.next(), read.next(), read.next(), read.next(), read.nextDouble()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteSelectedRowBtn() {
        if(showAlert()){
            Contract selectedContract = tableView.getSelectionModel().getSelectedItem();
            if (selectedContract != null) {
                contracteList.remove(selectedContract);
                contracte.remove(selectedContract);
                updateFile("src\\DataBase\\Contracte.txt");
            }
        }
        
    }
    private void updateFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contract contract : contracte) {
                writer.write(contract.getNrInregistrare() + " " + contract.getClientNume() + " " +
                             contract.getClientPrenume() + " " + contract.getNotarNume() + " " + contract.getNotarPrenume()
                             + " " + contract.getAngajatNume() + " " + contract.getAngajatPrenume() + " " + contract.getDataIntocmire()
                             + " " + contract.getDataValabilitate() + " " + contract.getValoare());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void AddRowBtn() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Add");
        alert.setHeaderText("Enter data:");

        TextField nrInregistrareInput = new TextField();
        nrInregistrareInput.setPromptText("nrInregistrare..");
        TextField clientNumeInput = new TextField();
        clientNumeInput.setPromptText("clientNume..");
        TextField clientPrenumeInput = new TextField();
        clientPrenumeInput.setPromptText("clientPrenume..");
        TextField notarNumeInput = new TextField();
        notarNumeInput.setPromptText("notarNume..");
        TextField notarPrenumeInput = new TextField();
        notarPrenumeInput.setPromptText("notarPrenume..");
        TextField angajatNumeInput = new TextField();
        angajatNumeInput.setPromptText("angajatNume..");
        TextField angajatPrenumeInput = new TextField();
        angajatPrenumeInput.setPromptText("angajatPrenume..");
        TextField dataIntocmireInput = new TextField();
        dataIntocmireInput.setPromptText("dataIntocmire..");
        TextField dataValabilitateInput = new TextField();
        dataValabilitateInput.setPromptText("dataValabilitate..");

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(nrInregistrareInput, clientNumeInput, clientPrenumeInput, notarNumeInput, notarPrenumeInput, angajatNumeInput, angajatPrenumeInput, dataIntocmireInput, dataValabilitateInput);

        GridPane gridPane = new GridPane();
        gridPane.add(hBox, 0, 0);
        alert.getDialogPane().setContent(gridPane);

        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == ButtonType.OK) {
                String nrInregistrare = nrInregistrareInput.getText();
                String clientNume = clientNumeInput.getText();
                String clientPrenume = clientPrenumeInput.getText();
                String notarNume = notarNumeInput.getText();
                String notarPrenume = notarPrenumeInput.getText();
                String angajatNume = angajatNumeInput.getText();
                String angajatPrenume = angajatPrenumeInput.getText();
                String dataIntocmire = dataIntocmireInput.getText();
                String dataValabilitate = dataValabilitateInput.getText();
                double valoare = 0;


                Contract newContract = new Contract(nrInregistrare, clientNume, clientPrenume, notarNume, notarPrenume, angajatNume, angajatPrenume, dataIntocmire, dataValabilitate, valoare);
                contracte.add(newContract);
                contracteList.add(newContract);

                updateFile("src\\DataBase\\Contracte.txt");
            }
        });

        alert.showAndWait();
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
    public void ShowEditRowBtn() {
        Contract selectedContract = tableView.getSelectionModel().getSelectedItem();
        if (selectedContract == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contract to edit.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Edit Contract");
        alert.setHeaderText("Edit Contract Details");

        TextField nrInregistrareInput = new TextField(selectedContract.getNrInregistrare());
        TextField clientNumeInput = new TextField(selectedContract.getClientNume());
        TextField clientPrenumeInput = new TextField(selectedContract.getClientPrenume());
        TextField notarNumeInput = new TextField(selectedContract.getNotarNume());
        TextField notarPrenumeInput = new TextField(selectedContract.getNotarPrenume());
        TextField angajatNumeInput = new TextField(selectedContract.getAngajatNume());
        TextField angajatPrenumeInput = new TextField(selectedContract.getAngajatPrenume());
        TextField dataIntocmireInput = new TextField(selectedContract.getDataIntocmire());
        TextField dataValabilitateInput = new TextField(selectedContract.getDataValabilitate());
        TextField valoareInput = new TextField(String.valueOf(selectedContract.getValoare()));

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Nr Inregistrare:"), 0, 0);
        gridPane.add(nrInregistrareInput, 1, 0);
        gridPane.add(new Label("Client Nume:"), 0, 1);
        gridPane.add(clientNumeInput, 1, 1);
        gridPane.add(new Label("Client Prenume:"), 0, 2);
        gridPane.add(clientPrenumeInput, 1, 2);
        gridPane.add(new Label("Notar Nume:"), 0, 3);
        gridPane.add(notarNumeInput, 1, 3);
        gridPane.add(new Label("Notar Prenume:"), 0, 4);
        gridPane.add(notarPrenumeInput, 1, 4);
        gridPane.add(new Label("Angajat Nume:"), 0, 5);
        gridPane.add(angajatNumeInput, 1, 5);
        gridPane.add(new Label("Angajat Prenume:"), 0, 6);
        gridPane.add(angajatPrenumeInput, 1, 6);
        gridPane.add(new Label("Data Intocmire:"), 0, 7);
        gridPane.add(dataIntocmireInput, 1, 7);
        gridPane.add(new Label("Data Valabilitate:"), 0, 8);
        gridPane.add(dataValabilitateInput, 1, 8);
        gridPane.add(new Label("Valoare:"), 0, 9);
        gridPane.add(valoareInput, 1, 9);

        alert.getDialogPane().setContent(gridPane);
        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == ButtonType.OK) {
                selectedContract.setNrInregistrare(nrInregistrareInput.getText());
                selectedContract.setClientNume(clientNumeInput.getText());
                selectedContract.setClientPrenume(clientPrenumeInput.getText());
                selectedContract.setNotarNume(notarNumeInput.getText());
                selectedContract.setNotarPrenume(notarPrenumeInput.getText());
                selectedContract.setAngajatNume(angajatNumeInput.getText());
                selectedContract.setAngajatPrenume(angajatPrenumeInput.getText());
                selectedContract.setDataIntocmire(dataIntocmireInput.getText());
                selectedContract.setDataValabilitate(dataValabilitateInput.getText());
                selectedContract.setValoare(Double.parseDouble(valoareInput.getText()));

                tableView.refresh();
                updateFile("src\\DataBase\\Contracte.txt");
            }
        });

        alert.showAndWait();
    }
}
