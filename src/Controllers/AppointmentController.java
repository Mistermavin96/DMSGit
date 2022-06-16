package Controllers;

import ClassesAndFunctions.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class AppointmentController {

    public static Appointment tempAppointment;
    @FXML public ComboBox<Customer> cmbCustomer;
    @FXML public ComboBox<User> cmbUser;
    @FXML public TextField txtStart;
    @FXML public TextField txtEnd;
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
        cmbCustomer.setItems(Customer.getAllCustomers());
        cmbUser.setItems(User.getAllUsers());
        if (tempAppointment != null) {
            cmbCustomer.setValue(Appointment.getCustomerByID(tempAppointment.getCustomer_ID()));
            txtAppointmentID.setText(String.valueOf(tempAppointment.getAppointment_ID()));
            cmbUser.setValue(Appointment.getUserByID(tempAppointment.getUser_ID()));
            txtTitle.setText(tempAppointment.getTitle());
            txtType.setText(tempAppointment.getType());
            txtDescription.setText(tempAppointment.getDescription());
            txtLocation.setText(tempAppointment.getLocation());
            txtStart.setText(tempAppointment.getStartString());
            txtEnd.setText(tempAppointment.getEndString());
            cmbContact.setValue(Contact.getContactByID(tempAppointment.getContact_ID()));
        }
    }

    @FXML
    public void OnSubmitButtonClick() {
            if (txtTitle.getText() != null && txtTitle.getText().length() <= 50) {
                if (txtDescription.getText() != null && txtDescription.getText().length() <= 50) {
                    if (txtLocation.getText() != null && txtLocation.getText().length() <= 50) {
                        if (txtType.getText() != null && txtType.getText().length() <= 50) {
                            if (txtStart.getText() != null && Pattern.matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})", txtStart.getText())) {
                                if (txtEnd.getText() != null && Pattern.matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})", txtEnd.getText())) {
                                    if (cmbCustomer.getSelectionModel().getSelectedItem() != null) {
                                        if (cmbUser.getSelectionModel().getSelectedItem() != null) {
                                            if (cmbContact.getSelectionModel().getSelectedItem() != null) {
                                                if (tempAppointment == null) {
                                                    if (Appointment.lastID() + 1 <= 999999999) {
                                                        Appointment newAppointment = new Appointment(
                                                                Appointment.lastID() + 1,
                                                                txtTitle.getText(),
                                                                txtDescription.getText(),
                                                                txtLocation.getText(),
                                                                txtType.getText(),
                                                                LocalDateTime.parse(txtStart.getText()),
                                                                LocalDateTime.parse(txtEnd.getText()),
                                                                cmbCustomer.getSelectionModel().getSelectedItem().getCustomer_ID(),
                                                                cmbUser.getSelectionModel().getSelectedItem().getUser_ID(),
                                                                cmbContact.getSelectionModel().getSelectedItem().getContact_ID());
                                                        Appointment.addAppointment(newAppointment);
                                                    } else {
                                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment ID overflow, cannot create appointment.");
                                                        alert.showAndWait();
                                                    }
                                                } else {
                                                        tempAppointment.setTitle(txtTitle.getText());
                                                        tempAppointment.setDescription(txtDescription.getText());
                                                        tempAppointment.setLocation(txtLocation.getText());
                                                        tempAppointment.setType(txtType.getText());
                                                        tempAppointment.setStart(LocalDateTime.parse(txtStart.getText()));
                                                        tempAppointment.setEnd(LocalDateTime.parse(txtEnd.getText()));
                                                        tempAppointment.setCustomer_ID(cmbCustomer.getSelectionModel().getSelectedItem().getCustomer_ID());
                                                        tempAppointment.setUser_ID(cmbUser.getSelectionModel().getSelectedItem().getUser_ID());
                                                        tempAppointment.setContact_ID(cmbContact.getSelectionModel().getSelectedItem().getContact_ID());
                                                    }
                                                } else {
                                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please choose a contact.");
                                                    alert.showAndWait();
                                                }
                                            } else {
                                                Alert alert = new Alert(Alert.AlertType.ERROR, "Please choose a user.");
                                                alert.showAndWait();
                                            }
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please choose a customer.");
                                            alert.showAndWait();
                                        }
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid date in the format YYYY-MM-DD HH:MM:SS.");
                                        alert.showAndWait();
                                    }
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid date in the format YYYY-MM-DD HH:MM:SS.");
                                    alert.showAndWait();
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid type with less than 50 characters.");
                                alert.showAndWait();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid location with less than 50 characters.");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid description with less than 50 characters.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid title with less than 50 characters.");
                    alert.showAndWait();
                }
            }

    @FXML
    public void OnCancelButtonClick() {
        DBConnect.closeConnection();
        Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
        currentWindow.close();
    }
}
