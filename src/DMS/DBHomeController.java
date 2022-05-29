package DMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

public class DBHomeController {
    public void OnWeekClick(ActionEvent actionEvent) {
    }

    public void OnMonthClick(ActionEvent actionEvent) {
    }

    public void onDeleteCustomerClick(ActionEvent actionEvent) {
    }

    public void onUpdateCustomerClick(ActionEvent actionEvent) {
    }

    public void onAddCustomerClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../sample/addCustomer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OnDeleteAppointmentClick(ActionEvent actionEvent) {
    }

    public void OnUpdateAppointmentClick(ActionEvent actionEvent) {
    }

    public void onAddAppointmentClick(ActionEvent actionEvent) {
    }
}
