package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Total {
    private int Total;
    private final Month Month;
    private final String Type;
    private final String MonthString;
    public static final ObservableList<Total> allTotals = FXCollections.observableArrayList();

    public Total(Month month, String type) {
        this.Month = month;
        this.Type = type;
        this.Total = 0;
        this.MonthString = Month.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public static void ModifyAppointment(Appointment tempAppointment, Month newMonth, String newType) {
        boolean isInList = false;
        for (Total y : allTotals) {
            if (y.Month == tempAppointment.getStart().getMonth() && y.Type.equals(tempAppointment.getType())) {
                y.Total -= 1;
                isInList = true;
                if (y.Total == 0) {
                    allTotals.remove(y);
                    isInList = false;
                }
            }
            if (y.Month == newMonth && y.Type.equals(newType)) {
                y.Total += 1;
                isInList = true;
            }
        }
        if (!isInList) {
            Total newTotal = new Total(newMonth, newType);
            newTotal.Total = 1;
            allTotals.add(newTotal);
        }
    }

    public String getType() {
        return Type;
    }

    public java.time.Month getMonth() {
        return Month;
    }

    public void setTotal(int total) {
        Total = total;
    }
    public int getTotal() {
        return Total;
    }

    public String getMonthString() {
        return MonthString;
    }

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
        for (Classes.Total allTotal : allTotals) {
            System.out.println(allTotal.Month + ", " + allTotal.Type + ", " + allTotal.Total);
        }
    }

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
