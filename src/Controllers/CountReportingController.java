package Controllers;

import Classes.Appointment;
import Classes.Total;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.Month;

public class CountReportingController {
    public TableView<Total> ApppointmentTable;
    public TableColumn<Total, String> A_Month;
    public TableColumn<Total, String> A_Type;
    public TableColumn<Total, Integer> A_Total;

    public void initialize() {
        ApppointmentTable.setItems(Total.allTotals);
        A_Month.setCellValueFactory(new PropertyValueFactory<>("MonthString"));
        A_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        A_Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
}
