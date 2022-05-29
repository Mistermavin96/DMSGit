package DMS;

import javafx.fxml.FXML;

public class UpdateCustomerController {

    @FXML
    public void OnSubmitButtonClick() {
        System.out.println("Submitted");
    }

    @FXML
    public void OnCancelButtonClick() {
        System.out.println("Canceled");
    }

    @FXML
    public void OnCountrySelect() {
        System.out.println("Country Selected");
    }

}