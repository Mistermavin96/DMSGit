package Controllers;

import ClassesAndFunctions.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    @FXML
    public void OnSubmitButtonClick() {
        FieldValidation validate = (String str, String regex) -> (str != null && str.matches(regex));
        if (tempCustomer == null) {
            Customer newCustomer = new Customer(
                    Customer.lastID()+1,
                    cmbFirstLevelDomain.getSelectionModel().getSelectedItem().getDivision_ID(),
                    txtCustomerName.getText(),
                    txtAddress.getText(),
                    txtPostalCode.getText(),
                    txtPhoneNumber.getText()
            );
            Customer.addCustomer(newCustomer);
        } else {
            tempCustomer.setAddress(txtAddress.getText());
            tempCustomer.setDivision_ID(cmbFirstLevelDomain.getSelectionModel().getSelectedItem().getDivision_ID());
            tempCustomer.setCustomer_Name(txtCustomerName.getText());
            tempCustomer.setPhone(txtPhoneNumber.getText());
            tempCustomer.setPostal_Code(txtPostalCode.getText());
            System.out.println("Updated");
        }


    }

    @FXML
    public void OnCancelButtonClick() {
        DBConnect.closeConnection();
        Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
        currentWindow.close();
    }

    @FXML
    public void OnCountrySelect() {
        int cmbCountryID = cmbCountry.getSelectionModel().getSelectedItem().getCountry_ID();
        cmbFirstLevelDomain.setItems(FirstLevelDivision.filterByID(cmbCountryID));
    }

}
