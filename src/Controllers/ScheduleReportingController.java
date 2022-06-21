package Controllers;

import ClassesAndFunctions.Appointment;
import ClassesAndFunctions.Contact;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScheduleReportingController {
    @FXML ComboBox<Contact> cmbContact;
    @FXML public Button btnCancel;

    @FXML public TableView<Appointment> tblAppointments;
        @FXML public TableColumn<Appointment, Integer> A_Appointment_ID;
        @FXML public TableColumn<Appointment, String> A_Title;
        @FXML public TableColumn<Appointment, String> A_Type;
        @FXML public TableColumn<Appointment, String> A_Description;
        @FXML public TableColumn<Appointment, String> A_Start;
        @FXML public TableColumn<Appointment, String> A_End;
        @FXML public TableColumn<Appointment, Integer> A_Customer_ID;


    public void initialize() {
        cmbContact.setItems(Contact.getAllContacts());
    }

    public void OnCancelButtonClick() {
        Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
        currentWindow.close();

    }

    public void OnContactComboClick() {
        Contact currentContact = cmbContact.getSelectionModel().getSelectedItem();
        tblAppointments.setItems(currentContact.getAppointmentsByContact());
        A_Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        A_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        A_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        A_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        A_Start.setCellValueFactory(new PropertyValueFactory<>("StartString"));
        A_End.setCellValueFactory(new PropertyValueFactory<>("EndString"));
        A_Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
    }
}
