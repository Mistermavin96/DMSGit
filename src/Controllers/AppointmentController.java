package Controllers;

import ClassesAndFunctions.Appointment;
import ClassesAndFunctions.Contact;
import ClassesAndFunctions.DBConnect;
import ClassesAndFunctions.FieldValidation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class AppointmentController {

    public static Appointment tempAppointment;
    @FXML public TextField txtCustomerID;
    @FXML public TextField txtUserID;
    @FXML public TextField txtEndDateAndTime;
    @FXML public TextField txtStartDateAndTime;
    @FXML public TextField txtType;
    @FXML public TextField txtLocation;
    @FXML public TextField txtDescription;
    @FXML public TextField txtTitle;
    @FXML public TextField txtAppointmentID;
    @FXML public ComboBox<Contact> cmbContact;
    @FXML public Button btnCancel;
    @FXML public Button btnSubmit;

    public void initialize() {
        cmbContact.setItems(Contact.getAllContacts());
        if (tempAppointment != null) {
            txtCustomerID.setText(String.valueOf(tempAppointment.getCustomer_ID()));
            txtAppointmentID.setText(String.valueOf(tempAppointment.getAppointment_ID()));
            txtUserID.setText(String.valueOf(tempAppointment.getUser_ID()));
            txtTitle.setText(tempAppointment.getTitle());
            txtType.setText(tempAppointment.getType());
            txtDescription.setText(tempAppointment.getDescription());
            txtLocation.setText(tempAppointment.getLocation());
            txtStartDateAndTime.setText(tempAppointment.getStart());
            txtEndDateAndTime.setText(tempAppointment.getEnd());
            cmbContact.setValue(Contact.getContactByID(tempAppointment.getContact_ID()));
        }
    }

    @FXML
    public void OnSubmitButtonClick() {
        FieldValidation validate = (String str, String regex) -> (str != null && str.matches(regex));
        if (tempAppointment == null) {
            Appointment newAppointment = new Appointment(
                    Appointment.lastID()+1,
                    txtTitle.getText(),
                    txtDescription.getText(),
                    txtLocation.getText(),
                    txtType.getText(),
                    txtStartDateAndTime.getText(),
                    txtEndDateAndTime.getText(),
                    parseInt(txtCustomerID.getText()),
                    parseInt(txtUserID.getText()),
                    cmbContact.getSelectionModel().getSelectedItem().getContact_ID());
            Appointment.addAppointment(newAppointment);
        } else {
            tempAppointment.setTitle(txtTitle.getText());
            tempAppointment.setDescription(txtDescription.getText());
            tempAppointment.setLocation(txtLocation.getText());
            tempAppointment.setType(txtType.getText());
            tempAppointment.setStart(txtStartDateAndTime.getText());
            tempAppointment.setEnd(txtEndDateAndTime.getText());
            tempAppointment.setCustomer_ID(parseInt(txtCustomerID.getText()));
            tempAppointment.setUser_ID(parseInt(txtUserID.getText()));
            tempAppointment.setContact_ID(cmbContact.getSelectionModel().getSelectedItem().getContact_ID());
        }
    }

    @FXML
    public void OnCancelButtonClick() {
        DBConnect.closeConnection();
        Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
        currentWindow.close();
    }
}
