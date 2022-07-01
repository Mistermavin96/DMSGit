package Controllers;

import Classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This is the Controller for the Customer FXML, and provides methods to add or modify a Customer.
 */
public class CustomerController {

    public static Customer tempCustomer;
    @FXML public TextField txtCustomerID;
    @FXML public TextField txtCustomerName;
    @FXML public TextField txtAddress;
    @FXML public TextField txtPostalCode;
    @FXML public TextField txtPhoneNumber;
    @FXML public ComboBox<Country> cmbCountry;
    @FXML public ComboBox<FirstLevelDivision> cmbFirstLevelDomain;
    @FXML public Button btnCancel;
    @FXML public Button btnSubmit;

    /**
     * This method fills the information into Text Fields and Combo Boxes and executes on run.
     */
    @FXML public void initialize() {
        cmbCountry.setItems(Country.getAllCountries());
        if (tempCustomer != null) {
            txtCustomerID.setPromptText(String.valueOf(tempCustomer.getCustomer_ID()));
            txtAddress.setText(tempCustomer.getAddress());
            txtCustomerName.setText(tempCustomer.getCustomer_Name());
            txtPhoneNumber.setText(tempCustomer.getPhone());
            txtPostalCode.setText(tempCustomer.getPostal_Code());
            cmbCountry.setValue(FirstLevelDivision.getCountryByDivisionId(tempCustomer.getDivision_ID()));
            cmbFirstLevelDomain.setValue(FirstLevelDivision.getDivisionbyDivisionID(tempCustomer.getDivision_ID()));
        }
    }

    /**
     * This method runs on clicking the Submit button, and handles field validation and running necessary methods to add or modify the Customer.
     */
    @FXML public void OnSubmitButtonClick() {
        if (cmbFirstLevelDomain.getSelectionModel().getSelectedItem() != null) {
            if (!txtCustomerName.getText().equals("") && txtCustomerName.getText().length() <= 50) {
                if (!txtAddress.getText().equals("") && txtAddress.getText().length() <= 100) {
                    if (!txtPhoneNumber.getText().equals("") && txtPhoneNumber.getText().length() <= 50) {
                        if (!txtPostalCode.getText().equals("") && txtPostalCode.getText().length() <= 50) {
                            if (tempCustomer == null) {
                                if (Customer.lastID() + 1 <= 999999999) {
                                    Customer newCustomer = new Customer(
                                            Customer.lastID() + 1,
                                            cmbFirstLevelDomain.getSelectionModel().getSelectedItem().getDivision_ID(),
                                            txtCustomerName.getText(),
                                            txtAddress.getText(),
                                            txtPostalCode.getText(),
                                            txtPhoneNumber.getText()
                                    );
                                    Customer.addCustomer(newCustomer);
                                    PreparedStatements.AddCustomer(newCustomer);
                                    Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
                                    currentWindow.close();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "ID out of bounds, cannot create customer.");
                                    alert.showAndWait();
                                }
                            } else {
                                tempCustomer.setAddress(txtAddress.getText());
                                tempCustomer.setDivision_ID(cmbFirstLevelDomain.getSelectionModel().getSelectedItem().getDivision_ID());
                                tempCustomer.setCustomer_Name(txtCustomerName.getText());
                                tempCustomer.setPhone(txtPhoneNumber.getText());
                                tempCustomer.setPostal_Code(txtPostalCode.getText());
                                PreparedStatements.ModifyCustomer(tempCustomer);
                                Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
                                currentWindow.close();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Postal code must not be empty and 50 characters or less.");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Phone number must not be empty and 50 characters or less.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Address must not be empty and 100 characters or less.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Name must not be empty and 50 characters or less.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please choose a country and divison.");
            alert.showAndWait();
        }
    }

    /**
     * This method runs on clicking the cancel button and is a more fancy way to close the window, cancel buttons are the future, embrace it.
     */
    @FXML public void OnCancelButtonClick() {
        Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
        currentWindow.close();
    }

    /**
     * This method runs upon selecting a country and handles setting the Division Combo Box based on what Country was selected.
     */
    @FXML public void OnCountrySelect() {
        int cmbCountryID = cmbCountry.getSelectionModel().getSelectedItem().getCountry_ID();
        cmbFirstLevelDomain.setItems(FirstLevelDivision.filterByID(cmbCountryID));
    }

}