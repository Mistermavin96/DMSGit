package Controllers;

import Classes.Appointment;
import Classes.Customer;
import Classes.PreparedStatements;
import Classes.Total;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
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
        @FXML TableColumn<Customer, String> C_Country;
        @FXML TableColumn<Customer, String> C_Division;

    @FXML public void initialize() {
        ObservableList<Customer> CustomerList = Customer.getAllCustomers();
        ObservableList<Appointment> AppointmentList = Appointment.getAllAppointments();
        int i = 0;
        boolean hasAppointment = false;
        while (i < Appointment.getAllAppointments().size()) {
            if (Appointment.getAllAppointments().get(i).getStart().until(ZonedDateTime.now(), ChronoUnit.MINUTES) <= 15) {
                String alertMessage = "You have appointment of ID " + Appointment.getAllAppointments().get(i).getAppointment_ID() + " at " + Appointment.getAllAppointments().get(i).getStart().toLocalTime() + " on " + Appointment.getAllAppointments().get(i).getStart().toLocalDate();
                hasAppointment = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMessage);
                alert.showAndWait();
            }
            i++;
        }
        if (!hasAppointment) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have no upcoming appointments");
            alert.showAndWait();
        }

            CustomerTable.setItems(CustomerList);
            C_Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            C_Name.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            C_Number.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            C_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            C_Division.setCellValueFactory(new PropertyValueFactory<>("Division_Name"));
            C_Country.setCellValueFactory(new PropertyValueFactory<>("Country_Name"));

            AppointmentTable.setItems(AppointmentList);
            A_Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            A_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            A_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            A_Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
            A_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            A_StartDate.setCellValueFactory(new PropertyValueFactory<>("StartString"));
            A_EndDate.setCellValueFactory(new PropertyValueFactory<>("EndString"));
            A_Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            A_User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
            A_Contact.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
            ObservableList<Month> MonthList = FXCollections.observableArrayList();
            MonthList.addAll(Month.values());
            ObservableList<Integer> WeekArray = FXCollections.observableArrayList();
            int c = 1;
            while (c < 53) {
                WeekArray.add(c);
                c++;
            }
    }

    public void onDeleteCustomerClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this customer?");
        Optional<ButtonType> confirmation = alert.showAndWait();
        Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();

        if (confirmation.get() == ButtonType.OK) {
            if (selectedCustomer != null) {
                if (selectedCustomer.hasAppointments(selectedCustomer.getCustomer_ID())) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please delete any appointments associated with this customer first.");
                    alert1.showAndWait();
                } else {
                    PreparedStatements.DeleteCustomer(CustomerTable.getSelectionModel().getSelectedItem());
                    Customer.deleteCustomer(selectedCustomer);
                    CustomerTable.refresh();
                }
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please select a customer to delete.");
                alert1.showAndWait();
            }
        }
    }

    public void onUpdateCustomerClick() throws IOException {
        if (CustomerTable.getSelectionModel().getSelectedItem() != null) {
            CustomerController.tempCustomer = CustomerTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/Customer.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnHidden(windowEvent -> CustomerTable.refresh());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer");
            alert.showAndWait();
        }
    }

    public void onAddCustomerClick() throws IOException {
        CustomerController.tempCustomer = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/Customer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(windowEvent -> CustomerTable.refresh());
    }

    public void OnDeleteAppointmentClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to delete this appointment?");
        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.get() == ButtonType.OK) {
            if (AppointmentTable.getSelectionModel().getSelectedItem() != null) {
                PreparedStatements.DeleteAppointment(AppointmentTable.getSelectionModel().getSelectedItem());
                Total.AddOrRemoveAppointment(AppointmentTable.getSelectionModel().getSelectedItem(), true);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Appointment ID " + AppointmentTable.getSelectionModel().getSelectedItem().getAppointment_ID() + " has been deleted, of type " + AppointmentTable.getSelectionModel().getSelectedItem().getType() + ".");
                alert1.showAndWait();
                Appointment.deleteAppointment(AppointmentTable.getSelectionModel().getSelectedItem());
                AppointmentTable.refresh();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Please select an appointment to delete");
                alert1.showAndWait();
            }
        }
    }

    public void OnUpdateAppointmentClick() throws IOException {
        if (AppointmentTable.getSelectionModel().getSelectedItem() != null) {
            AppointmentController.tempAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/Appointment.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnHidden(windowEvent -> AppointmentTable.refresh());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment");
            alert.showAndWait();
        }
    }

    public void onAddAppointmentClick() throws IOException {
        AppointmentController.tempAppointment = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/Appointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(windowEvent -> AppointmentTable.refresh());
    }

    public void OnScheduleClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/ScheduleReporting.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OnCountClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/CountReporting.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OnTotalsClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The current number of customers is " + Customer.getAllCustomers().size() + " and the current number of appointments is " + Appointment.getAllAppointments().size());
        alert.showAndWait();
    }

    public void onByMonthClick() {
        AppointmentTable.setItems(Appointment.getAllAppointments().filtered(t -> t.getStart().getMonth() == LocalDateTime.now().getMonth() && t.getStart().getYear() == LocalDateTime.now().getYear()));
    }


    public void OnDefaultClick() {
        AppointmentTable.setItems(Appointment.getAllAppointments().filtered(t -> true));
    }

    public void onByWeekClick() {
        AppointmentTable.setItems(Appointment.getAllAppointments().filtered(t -> t.getStart().get(ChronoField.ALIGNED_WEEK_OF_YEAR) == LocalDateTime.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR) && t.getStart().getYear() == LocalDateTime.now().getYear()));
    }
}
