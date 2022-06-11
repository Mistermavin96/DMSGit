package DMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInController {

    @FXML public TextField txtUsername;
    @FXML public PasswordField txtPassword;
    @FXML public Button btnSignIn;
    @FXML public Label lblLocation;
    @FXML public Label lblPrompt;
    public static ResultSet loginInfo;

    @FXML private void initialize() {
        ResourceBundle loginBundle = ResourceBundle.getBundle("LoginResource");
        txtUsername.setPromptText(loginBundle.getString("txtUserPrompt"));
        txtPassword.setPromptText(loginBundle.getString("txtPassPrompt"));
        btnSignIn.setText(loginBundle.getString("btnSignIn"));
        lblPrompt.setText(loginBundle.getString("lblPrompt"));
        lblLocation.setText(loginBundle.getString("lblLocation") + Locale.getDefault().getDisplayCountry());
    }
    
    @FXML
    protected void onSignInButtonClick(ActionEvent e) throws IOException {
        ResourceBundle loginBundle = ResourceBundle.getBundle("LoginResource");
        ButtonType btnConfirm= new ButtonType(loginBundle.getString("errConfirm"));

        DBConnect.openConnection();

        String loginQuery = "SELECT * FROM users";
        try (Statement stmt = DBConnect.connection.createStatement()) {
            loginInfo = stmt.executeQuery(loginQuery);
            try {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
                FileWriter fw = new FileWriter("login_activity.txt",  true);
                PrintWriter out = new PrintWriter(fw);
                out.print("Time Attempted: " + timeStamp + "\t");
                out.print("Attempt Status: ");

                if (Objects.equals(txtUsername.getText(), "")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, loginBundle.getString("errEmptyUser"), btnConfirm);
                    alert.showAndWait();
                    out.print("Failed\n");
                    out.close();
                    DBConnect.closeConnection();
                    return;

                } else if (Objects.equals(txtPassword.getText(), "")){
                    Alert alert = new Alert(Alert.AlertType.ERROR, loginBundle.getString("errEmptyPass"), btnConfirm);
                    alert.showAndWait();
                    out.print("Failed\n");
                    out.close();
                    DBConnect.closeConnection();
                    return;

                } else while (loginInfo.next()) {
                    if (loginInfo.getString("User_Name").equals(txtUsername.getText()) && loginInfo.getString("Password").equals(txtPassword.getText())) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/sample/DBHome.fxml"));
                        Parent root = fxmlLoader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("AppointX");
                        stage.show();
                        out.print("Successful\n");
                        out.close();
                        DBConnect.closeConnection();
                        return;
                    }
                }
                Alert alert = new Alert(Alert.AlertType.ERROR, loginBundle.getString("errWrongLogin"), btnConfirm);
                alert.showAndWait();
                out.print("Failed\n");
                out.close();
                DBConnect.closeConnection();
            } catch (Exception e1){
                Alert alert = new Alert(Alert.AlertType.ERROR, loginBundle.getString("errLogFail"), btnConfirm);
                System.out.println(e1);
                alert.showAndWait();
                DBConnect.closeConnection();
            }
        } catch (SQLException e2) {
            System.out.println(e2);
            DBConnect.closeConnection();
        }
    }

}
