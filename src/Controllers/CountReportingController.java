package Controllers;

import Classes.Total;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class is a controller for the CountReporting FXML, and fills a table with data from the Total objects.
 */
public class CountReportingController {
    public TableView<Total> ApppointmentTable;
    public TableColumn<Total, String> A_Month;
    public TableColumn<Total, String> A_Type;
    public TableColumn<Total, Integer> A_Total;

    /**
     * This method fills the table with data on run.
     */
    public void initialize() {
        ApppointmentTable.setItems(Total.allTotals);
        A_Month.setCellValueFactory(new PropertyValueFactory<>("MonthString"));
        A_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        A_Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
}