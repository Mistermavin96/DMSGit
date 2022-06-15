package Controllers;

import ClassesAndFunctions.SetupDatabaseData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SetupDatabaseData.setup();
        ResourceBundle loginBundle = ResourceBundle.getBundle("LoginResource");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle(loginBundle.getString("ttlSignIn"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
