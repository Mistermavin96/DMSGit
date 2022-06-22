package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class Appointment {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private ZonedDateTime Start;
    private ZonedDateTime End;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private String User_Name;
    private String Customer_Name;
    private String Contact_Name;
    private String StartString;
    private String EndString;
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, ZonedDateTime Start, ZonedDateTime End, int Customer_ID, int User_ID, int Contact_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
        this.StartString = Start.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(SetupDatabaseData.timeDateFormat);
        this.EndString = End.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(SetupDatabaseData.timeDateFormat);
        this.Contact_Name = Objects.requireNonNull(Contact.getContactByID(Contact_ID)).getContact_Name();
        this.User_Name = Objects.requireNonNull(getUserByID(User_ID)).getUsername();
        this.Customer_Name = Objects.requireNonNull(getCustomerByID(Customer_ID)).getCustomer_Name();
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
        Contact_Name = Objects.requireNonNull(Contact.getContactByID(Contact_ID)).getContact_Name();
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
        Customer_Name = Objects.requireNonNull(getCustomerByID(Customer_ID)).getCustomer_Name();
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setEnd(ZonedDateTime end) {
        End = end;
        EndString = End.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(SetupDatabaseData.timeDateFormat);
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setStart(ZonedDateTime start) {
        Start = start;
        StartString = Start.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(SetupDatabaseData.timeDateFormat);
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEndString() {
        return EndString;
    }

    public String getStartString() {
        return StartString;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
        User_Name = Objects.requireNonNull(getUserByID(User_ID)).getUsername();
    }

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public String getDescription() {
        return Description;
    }

    public ZonedDateTime getEnd() { return End;}

    public String getLocation() {
        return Location;
    }

    public ZonedDateTime getStart() {
        return Start;
    }

    public String getTitle() {
        return Title;
    }

    public String getType() {
        return Type;
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static void addAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);
    }

    public static void deleteAppointment(Appointment newAppointment) {
        allAppointments.remove(newAppointment);
    }

    public static int lastID() {
        int i = 0;
        int MaxID = 0;
        while (i < allAppointments.size()) {
            MaxID = allAppointments.get(i).getAppointment_ID();
            i++;
        }
        return MaxID;
    }

    public static Customer getCustomerByID(int ID) {
        int i = 0;
        while (i < Customer.getAllCustomers().size()) {
            if (Customer.getAllCustomers().get(i).getCustomer_ID() == ID) {
                return Customer.getAllCustomers().get(i);
            }
            i++;
        }
        return null;
    }

    public static User getUserByID(int ID) {
        int i = 0;
        while (i < User.getAllUsers().size()) {
            if (User.getAllUsers().get(i).getUser_ID() == ID) {
                return User.getAllUsers().get(i);
            }
            i++;
        }
        return null;
    }

    public static boolean dateValidator(String date) {
        try {
            LocalDate.parse(date, SetupDatabaseData.timeDateFormat);
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }

    public static boolean inHoursValidator(ZonedDateTime timeStart, ZonedDateTime timeEnd) {
        ZonedDateTime estTimeStart = timeStart.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime estTimeEnd = timeEnd.withZoneSameInstant(ZoneId.of("America/New_York"));
        return estTimeStart.getHour() >= 8 && estTimeStart.getHour() <= 22 && estTimeEnd.getHour() >= 8 && estTimeEnd.getHour() <= 22 && DAYS.between(estTimeStart, estTimeEnd) < 1;
    }

    public static boolean selfOverlapValidator(ZonedDateTime timeStart, ZonedDateTime timeEnd) {
        return timeEnd.isAfter(timeStart);
    }

    public static boolean overlapValidator(int CustomerID, ZonedDateTime startTime, ZonedDateTime endTime, int AppointmentID) {
        int i = 0;
        while (i < Appointment.getAllAppointments().size()) {
            if (CustomerID == Appointment.getAllAppointments().get(i).Customer_ID && AppointmentID != Appointment.getAllAppointments().get(i).getAppointment_ID()) {
                if (!(startTime.isAfter(Appointment.getAllAppointments().get(i).End) ||
                        endTime.isBefore(Appointment.getAllAppointments().get(i).Start) ||
                        startTime.isEqual(Appointment.getAllAppointments().get(i).Start) ||
                        endTime.isEqual(Appointment.getAllAppointments().get(i).End))) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
}
