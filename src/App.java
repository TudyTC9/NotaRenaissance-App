import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{


    static ArrayList<User> users = new ArrayList<>();
    static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        Parent loginPage = FXMLLoader.load(getClass().getResource("Interface\\LogInPage.fxml"));
        
        loadUsersFromFileUsers("src\\DataBase\\Users.txt");
        primaryStage.setResizable(false);
        primaryStage.setTitle("Nota Renaissance");
        primaryStage.setScene(new Scene(loginPage));
        primaryStage.show();

    }
    public void changeScene(String fxml){
        Parent loadScene;
        try {
            loadScene = FXMLLoader.load(getClass().getResource(fxml));
            stage.getScene().setRoot(loadScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void loadUsersFromFileUsers(String filePath) {
        try (Scanner read = new Scanner(new FileReader(new File(filePath)))) {
            while (read.hasNext()) {
                users.add(new User(read.next(), read.next(), read.next(), read.next(), read.next(), read.next(), read.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}