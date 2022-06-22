package Controllers;

import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        if (!txtTitle.getText().equals("") && txtTitle.getText().length() <= 50) {
            if (!txtDescription.getText().equals("") && txtDescription.getText().length() <= 50) {
                if (!txtLocation.getText().equals("") && txtLocation.getText().length() <= 50) {
                    if (!txtType.getText().equals("") && txtType.getText().length() <= 50) {
                        if (Appointment.dateValidator(txtStart.getText())) {
                            if (Appointment.dateValidator(txtEnd.getText())) {
                                if (cmbCustomer.getSelectionModel().getSelectedItem() != null) {
                                    if (cmbUser.getSelectionModel().getSelectedItem() != null) {
                                        if (cmbContact.getSelectionModel().getSelectedItem() != null) {
                                            ZonedDateTime StartZone = ZonedDateTime.ofInstant(LocalDateTime.parse(txtStart.getText(), SetupDatabaseData.timeDateFormat), OffsetDateTime.now().getOffset(), ZoneId.systemDefault());
                                            ZonedDateTime EndZone = ZonedDateTime.ofInstant(LocalDateTime.parse(txtEnd.getText(), SetupDatabaseData.timeDateFormat), OffsetDateTime.now().getOffset(), ZoneId.systemDefault());
                                            if (Appointment.selfOverlapValidator(StartZone, EndZone)) {
                                                if (Appointment.inHoursValidator(StartZone, EndZone)) {
                                                    if (tempAppointment == null) {
                                                        if (Appointment.lastID() + 1 <= 999999999) {
                                                            if (Appointment.overlapValidator(cmbCustomer.getSelectionModel().getSelectedItem().getCustomer_ID(), StartZone, EndZone, Appointment.lastID()+1)) {
                                                                Appointment newAppointment = new Appointment(
                                                                        Appointment.lastID() + 1,
                                                                        txtTitle.getText(),
                                                                        txtDescription.getText(),
                                                                        txtLocation.getText(),
                                                                        txtType.getText(),
                                                                        StartZone,
                                                                        EndZone,
                                                                        cmbCustomer.getSelectionModel().getSelectedItem().getCustomer_ID(),
                                                                        cmbUser.getSelectionModel().getSelectedItem().getUser_ID(),
                                                                        cmbContact.getSelectionModel().getSelectedItem().getContact_ID());
                                                                Appointment.addAppointment(newAppointment);
                                                                PreparedStatements.AddAppointment(newAppointment);
                                                                Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
                                                                currentWindow.close();
                                                                } else {
                                                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Customer already has appointment for that time.");
                                                                    alert.showAndWait();
                                                                }
                                                            } else {
                                                                Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment ID overflow, cannot create appointment.");
                                                                alert.showAndWait();
                                                            }
                                                        } else {
                                                            if (Appointment.overlapValidator(cmbCustomer.getSelectionModel().getSelectedItem().getCustomer_ID(), StartZone, EndZone, tempAppointment.getAppointment_ID())) {
                                                                tempAppointment.setTitle(txtTitle.getText());
                                                                tempAppointment.setDescription(txtDescription.getText());
                                                                tempAppointment.setLocation(txtLocation.getText());
                                                                tempAppointment.setType(txtType.getText());
                                                                tempAppointment.setStart(ZonedDateTime.ofInstant(LocalDateTime.parse(txtStart.getText(), SetupDatabaseData.timeDateFormat), OffsetDateTime.now().getOffset(), ZoneId.systemDefault()));
                                                                tempAppointment.setEnd(ZonedDateTime.ofInstant(LocalDateTime.parse(txtEnd.getText(), SetupDatabaseData.timeDateFormat), OffsetDateTime.now().getOffset(), ZoneId.systemDefault()));
                                                                tempAppointment.setCustomer_ID(cmbCustomer.getSelectionModel().getSelectedItem().getCustomer_ID());
                                                                tempAppointment.setUser_ID(cmbUser.getSelectionModel().getSelectedItem().getUser_ID());
                                                                tempAppointment.setContact_ID(cmbContact.getSelectionModel().getSelectedItem().getContact_ID());
                                                                PreparedStatements.ModifyAppointment(tempAppointment);
                                                                Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
                                                                currentWindow.close();
                                                            } else {
                                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer already has appointment for that time.");
                                                            alert.showAndWait();
                                                            }
                                                        }
                                                } else {
                                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment must be during service hours (8a-10p est).");
                                                    alert.showAndWait();
                                                }
                                            } else {
                                                Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment start time must not be after end time.");
                                                alert.showAndWait();
                                            }
                                        }else {
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
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid end date in the format YYYY-MM-DD HH:MM:SS.");
                                alert.showAndWait();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid start date in the format YYYY-MM-DD HH:MM:SS.");
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
