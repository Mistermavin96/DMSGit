package DMS;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML public TextField txtCustomerID;
    @FXML public TextField txtCustomerName;
    @FXML public TextField txtAddress;
    @FXML public TextField txtPostalCode;
    @FXML public TextField txtPhoneNumber;
    @FXML public static ComboBox<Country> cmbCountry;
    @FXML public ComboBox<FirstLevelDomain> cmbFirstLevelDomain;
    @FXML public Button btnCancel;
    @FXML public Button btnSubmit;

    public static void initialize() {
        DBConnect.openConnection();
        cmbCountry.getItems().addAll(Country.getAllCountries());
    }

    @FXML
    public void OnSubmitButtonClick() {
        System.out.println("Submitted");
    }

    @FXML
    public void OnCancelButtonClick() {
        DBConnect.closeConnection();
        Stage currentWindow = (Stage) btnCancel.getScene().getWindow();
        currentWindow.close();
    }

    @FXML
    public void OnCountrySelect() {
        System.out.println("Country Selected");
    }

}
