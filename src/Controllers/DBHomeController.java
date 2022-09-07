package Controllers;

import Classes.Appointment;
import Classes.Customer;
import Classes.PreparedStatements;
import Classes.Total;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * This class is the Controller for the main screen of the program, and has methods to handle all sorts of choices the user could make.
 */
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

        @FXML TextField txtSearch;

    /**
     * This method executes on run, and populates table with values, and alerts the user of upcoming appointments.
     */
    @FXML public void initialize() {
        ObservableList<Customer> CustomerList = Customer.getAllCustomers();
        ObservableList<Appointment> AppointmentList = Appointment.getAllAppointments();
        boolean hasAppointment = false;
        for (Appointment x : Appointment.getAllAppointments()) {
            if (ZonedDateTime.now().until(x.getStart(), ChronoUnit.MINUTES) <= 15 && ZonedDateTime.now().until(x.getStart(), ChronoUnit.MINUTES) >= 0) {
                String alertMessage = "You have appointment of ID " + x.getAppointment_ID() + " at " + x.getStart().toLocalTime() + " on " + x.getStart().toLocalDate();
                hasAppointment = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMessage);
                alert.showAndWait();
            }
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
    }

    /**
     * This method runs when the delete customer button is clicked, and handles confirmation and deletion.
     */
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

    /**
     * This method is run on clicking the update customer button, and handles opening the proper version of the Customer FXML. It also uses a lambda to refresh the customer table once the window is closed.
     */
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

    public void onSearchType() throws IOException {
        if (txtSearch.getText().equals("")) {
            CustomerTable.setItems(Customer.getAllCustomers());
        } else {
            CustomerTable.setItems(Customer.getAllCustomers().filtered(t -> String.valueOf(t.getCustomer_ID()).equals(txtSearch.getText()) || t.getCustomer_Name().contains(txtSearch.getText())));
        }
    }

    /**
     * This method is run on clicking the add customer button, and handles opening the proper version of the Customer FXML. It also uses a lambda to refresh the customer table once the window is closed.
     */
    public void onAddCustomerClick() throws IOException {
        CustomerController.tempCustomer = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/Customer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(windowEvent -> CustomerTable.refresh());
    }

    /**
     * This method runs when the delete Appointment button is clicked, and handles confirmation and deletion.
     */
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

    /**
     * This method is run on clicking the update Appointmetn button, and handles opening the proper version of the Appointment FXML. It also uses a lambda to refresh the Appointment table once the window is closed.
     */
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

    /**
     * This method is run on clicking the add Appointment button, and handles opening the proper version of the Appointment FXML. It also uses a lambda to refresh the Appointment table once the window is closed.
     */
    public void onAddAppointmentClick() throws IOException {
        AppointmentController.tempAppointment = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/Appointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(windowEvent -> AppointmentTable.refresh());
    }

    /**
     * This method runs upon clicking the Schedule By Contact button and handles opening the ScheduleReporting FXML
     */
    public void OnScheduleClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/ScheduleReporting.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method runs when the Appointment Count button is clicked, and handles opening the CountReporting FXML.
     */
    public void OnCountClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../FXML/CountReporting.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method runs when the Totals button is clicked and opens a popup window showing the number of customers and appointments.
     */
    public void OnTotalsClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "The current number of customers is " + Customer.getAllCustomers().size() + " and the current number of appointments is " + Appointment.getAllAppointments().size());
        alert.showAndWait();
    }

    /**
     * This method runs when clicking the by month button, and uses a lambda as a predicate to filter the table to only appointments this month.
     */
    public void onByMonthClick() { AppointmentTable.setItems(Appointment.getAllAppointments().filtered(t -> t.getStart().getMonth() == LocalDateTime.now().getMonth() && t.getStart().getYear() == LocalDateTime.now().getYear())); }

    /**
     * This method resets the table to display all appointments.
     */
    public void OnDefaultClick() {
        AppointmentTable.setItems(Appointment.getAllAppointments());
    }

    /**
     * This method uses a lambda as a predicate to filter the table to only show appointments that are in the ISO alligned week of this year.
     */
    public void onByWeekClick() { AppointmentTable.setItems(Appointment.getAllAppointments().filtered(t -> t.getStart().get(ChronoField.ALIGNED_WEEK_OF_YEAR) == LocalDateTime.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR) && t.getStart().getYear() == LocalDateTime.now().getYear())); }
}