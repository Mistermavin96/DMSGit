package DMS;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;
import java.util.Optional;

public class DBHomeController {

    @FXML TableView<Appointment> ApppointmentTable;
        @FXML TableColumn<Appointment, Integer> A_Appointment_ID;
        @FXML TableColumn<Appointment, String> A_Title;
        @FXML TableColumn<Appointment, String> A_Description;
        @FXML TableColumn<Appointment, String> A_Location;
        @FXML TableColumn<Appointment, Integer> A_Contact;
        @FXML TableColumn<Appointment, String> A_Type;
        @FXML TableColumn<Appointment, String> A_EndDate;
        @FXML TableColumn<Appointment, String> A_StartDate;
        @FXML TableColumn<Appointment, Integer> A_Customer_ID;
        @FXML TableColumn<Appointment, Integer> A_User_ID;


    @FXML TableView<Customer> CustomerTable;
        @FXML TableColumn<Customer, Integer> C_Customer_ID;
        @FXML TableColumn<Customer, String> C_Name;
        @FXML TableColumn<Customer, String> C_Number;
        @FXML TableColumn<Customer, String> C_Address;

    public void OnWeekClick(ActionEvent actionEvent) {
    }

    public void OnMonthClick(ActionEvent actionEvent) {
    }

    public void onDeleteCustomerClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this customer?");
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
            System.out.println("ok");
        }
    }

    public void onUpdateCustomerClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/updateCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onAddCustomerClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/addCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OnDeleteAppointmentClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this appointment?");
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
            System.out.println("ok");
        }
    }

    public void OnUpdateAppointmentClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/updateAppointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onAddAppointmentClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/addAppointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
