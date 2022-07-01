package Controllers;

import Classes.SetupDatabaseData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * This is the main class of the program.
 */
public class Main extends Application {

    /**
     *This method sets up the program and starts it, it also sets the title to a language friendly string.
     */
    @Override public void start(Stage stage) throws IOException {
        SetupDatabaseData.setup();
        ResourceBundle loginBundle = ResourceBundle.getBundle("LoginResource");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle(loginBundle.getString("ttlSignIn"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method, and it launches the program.
     */
    public static void main(String[] args) {
        launch();
    }
}