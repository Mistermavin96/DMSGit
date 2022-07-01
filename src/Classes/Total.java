package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * This class creates the Total object for usage to track Appointment totals.
 */
public class Total {
    private int Total;
    private final Month Month;
    private final String Type;
    private final String MonthString;
    public static final ObservableList<Total> allTotals = FXCollections.observableArrayList();

    /**
     * This method is the constructor for the Total object.
     * @param month The Month the Appointment starts in to have its total calculated.
     * @param type The Type of Appointment to have its total calculated.
     */
    public Total(Month month, String type) {
        this.Month = month;
        this.Type = type;
        this.Total = 0;
        this.MonthString = Month.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    /**
     * This method handles changing the totals upon modifying an Appointment.
     * @param tempAppointment The old Appointment.
     * @param newMonth The Month of the new Apppointment.
     * @param newType The Type of the new Appointment.
     */
    public static void ModifyAppointment(Appointment tempAppointment, Month newMonth, String newType) {
        if (!(newMonth == tempAppointment.getStart().getMonth() && newType.equals(tempAppointment.getType()))) {
            boolean isInList = false;
            Total toBeRemoved = null;

            for (Total y : allTotals) {
                if (y.Month == tempAppointment.getStart().getMonth() && y.Type.equals(tempAppointment.getType())) {
                    y.Total -= 1;
                    if (y.Total == 0) {
                        toBeRemoved = y;
                    }
                }
            }

            if (toBeRemoved != null) {
                allTotals.remove(toBeRemoved);
            }

            for (Total y : allTotals) {
                if (y.Month == newMonth && y.Type.equals(newType)) {
                    y.Total += 1;
                    isInList = true;
                }
            }

            if (!isInList) {
                Total newTotal = new Total(newMonth, newType);
                newTotal.Total += 1;
                allTotals.add(newTotal);
            }
        }
    }

    /**
     * This method is the getter for the Type of Appointment to be totaled.
     * @return The Type of Appointment.
     */
    public String getType() { return Type; }

    /**
     * This method is the getter for the total of the combination of Month and Type, mostly used for tables.
     * @return The Total number for the Month and Type of Appointment.
     */
    public int getTotal() { return Total; }

    /**
     * This method is the getter for the String value of the Month of the Appointments to be totaled, mostly used for tables.
     * @return The String value of the Month.
     */
    public String getMonthString() { return MonthString; }

    /**
     * This method is designed to be used on setup, to get an initial list of totals from the Appointments.
     */
    public static void setAllTotals() {

        for (Appointment x : Appointment.getAllAppointments()) {
            boolean isInList = false;
            Total tempTotal = new Total(x.getStart().getMonth(), x.getType());
            for (Total y : allTotals) {
                if (y.Type.equals(tempTotal.Type) && y.Month == tempTotal.Month) {
                    isInList = true;
                    break;
                }
            }
            if (!isInList) {
                allTotals.add(tempTotal);
            }
        }

        for (Classes.Total allTotal: allTotals) {
            for(Appointment x : Appointment.getAllAppointments()) {
                if (x.getStart().getMonth() == allTotal.Month && x.getType().equals(allTotal.Type)) {
                    allTotal.Total += 1;
                }
            }
        }
    }

    /**
     * This method modifies the list of totals when adding or removing an Appointment.
     * @param a The Appointment being added or removed.
     * @param del A boolean which if true signifies a deletion as opposed to an addition.
     */
    public static void AddOrRemoveAppointment(Appointment a, boolean del) {
        for (Total x : allTotals) {
            if (x.Month == a.getStart().getMonth() && x.Type.equals(a.getType())) {
                if (del) {
                    x.Total -= 1;
                    if (x.Total == 0) {
                        allTotals.remove(x);
                    }
                    return;
                }
               x.Total += 1;
               return;
            }
        }
        Total newTotal = new Total(a.getStart().getMonth(), a.getType());
        allTotals.add(newTotal);
    }
}