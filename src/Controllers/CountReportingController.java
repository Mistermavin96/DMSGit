package Controllers;

import Classes.Appointment;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CountReportingController {
    public TableView<Appointment> ApppointmentTable;
    public TableColumn<Appointment, String> A_Month;
    public TableColumn<Appointment, String> A_Type;
    public TableColumn<Appointment, Integer> A_Total;

    public void initialize() {
        A_Month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        A_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        A_Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
}
