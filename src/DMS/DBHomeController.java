package DMS;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;

import java.io.IOException;
import java.util.Optional;

public class DBHomeController {

    @FXML TableView<Appointment> AppointmentTable;
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

    @FXML private void initialize() {
        ObservableList<Customer> CustomerList = Customer.getAllCustomers();
        ObservableList<Appointment> AppointmentList = Appointment.getAllAppointments();

            CustomerTable.setItems(CustomerList);
            C_Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            C_Name.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            C_Number.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            C_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));

            AppointmentTable.setItems(AppointmentList);
            A_Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            A_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            A_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            A_Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
            A_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            A_StartDate.setCellValueFactory(new PropertyValueFactory<>("Start"));
            A_EndDate.setCellValueFactory(new PropertyValueFactory<>("End"));
            A_Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            A_User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
            A_Contact.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
    }

    public void OnWeekClick() {
    }

    public void OnMonthClick() {
    }

    public void onDeleteCustomerClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this customer?");
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
            if (CustomerTable.getSelectionModel().getSelectedItem() != null) {
                Customer.deleteCustomer(CustomerTable.getSelectionModel().getSelectedItem());
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please select a customer to delete");
                alert1.showAndWait();
            }
        }
    }

    public void onUpdateCustomerClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/updateCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onAddCustomerClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/addCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OnDeleteAppointmentClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this appointment?");
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
            if (AppointmentTable.getSelectionModel().getSelectedItem() != null) {
                Appointment.deleteAppointment(AppointmentTable.getSelectionModel().getSelectedItem());
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please select an appointment to delete");
                alert1.showAndWait();
            }
        }
    }

    public void OnUpdateAppointmentClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/updateAppointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void onAddAppointmentClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/addAppointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
