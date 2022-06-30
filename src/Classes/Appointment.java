package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * This class creates the Appointment object which is used to track appointments.
 *
 */
public class Appointment {
    private final int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private ZonedDateTime Start;
    private ZonedDateTime End;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private String Contact_Name;
    private String StartString;
    private String EndString;
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();


    /**
     * This method is the Constructor for the object.
     * @param Appointment_ID Unique identifier for Appointment.
     * @param Title What the Appointment is called.
     * @param Description A more detailed description of what the Appointment is about.
     * @param Location Where the Appointment is taking place.
     * @param Type What kind of Appointment this is.
     * @param Start What time and date the Appointment will start.
     * @param End What time and date the Appointment will end.
     * @param Customer_ID Unique identifier for Customer that the Appointment is for.
     * @param User_ID Unique identifier for the User that the Appointment is for.
     * @param Contact_ID Unique identifier for the Contact that the Appointment is for.
     */
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
    }

    /**
     * This method is the getter for Contact Name.
     * @return The Contact Name for the Appointment.
     */
    public String getContact_Name() { return Contact_Name; }

    /**
     * This method is the setter for Contact ID. This method also resets the Contact Name according to the ID provided.
     * @param contact_ID The Contact ID associated with the Appointment.
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
        Contact_Name = Objects.requireNonNull(Contact.getContactByID(Contact_ID)).getContact_Name();
    }

    /**
     * The setter for the Customer ID.
     * @param customer_ID The Customer ID associated with the Appointment.
     */
    public void setCustomer_ID(int customer_ID) { Customer_ID = customer_ID; }

    /**
     * This method is the setter for the Description.
     * @param description The description for the Appointment.
     */
    public void setDescription(String description) { Description = description; }

    /**
     * This method is the setter for the End. It also sets the End String according to what is provided.
     * @param end The End of the Appointment
     */
    public void setEnd(ZonedDateTime end) {
        End = end;
        EndString = End.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(SetupDatabaseData.timeDateFormat);
    }

    /**
     * This method is the setter for the Location.
     * @param location The Location of the Appointment.
     */
    public void setLocation(String location) { Location = location; }

    /**
     * This method is the setter for the Start. It also sets the Start String according to what is provided.
     * @param start The Start of the Appointment.
     */
    public void setStart(ZonedDateTime start) {
        Start = start;
        StartString = Start.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(SetupDatabaseData.timeDateFormat);
    }

    /**
     * This method is the setter for the Title.
     * @param title The Title of the Appointment.
     */
    public void setTitle(String title) { Title = title; }

    /**
     * This method is the setter for the Type.
     * @param type The Type of the Appointment.
     */
    public void setType(String type) { Type = type; }

    /**
     * This method is the getter for the End String.
     * @return The String value of the End of the Appointment.
     */
    public String getEndString() { return EndString; }

    /**
     * This method is the getter for the Start String.
     * @return The String value of the Start of the Appointment.
     */
    public String getStartString() { return StartString; }

    /**
     * This method is the setter for the User ID.
     * @param user_ID The User ID associated with the Appointment.
     */
    public void setUser_ID(int user_ID) { User_ID = user_ID; }

    /**
     * This method is the getter for the Appointment ID.
     * @return The unique ID associated with the Appointment.
     */
    public int getAppointment_ID() { return Appointment_ID; }

    /**
     * This method is the getter for the Contact ID.
     * @return The Contact ID associated with the Appointment.
     */
    public int getContact_ID() { return Contact_ID; }

    /**
     * This method is the getter for the Customer ID.
     * @return The Customer ID associated with the Appointment.
     */
    public int getCustomer_ID() { return Customer_ID; }

    /**
     * This method is the getter for the User ID.
     * @return The User ID associated with the Appointment.
     */
    public int getUser_ID() { return User_ID; }

    /**
     * This method is the getter for the Description.
     * @return The Description of the Appointment.
     */
    public String getDescription() { return Description; }

    /**
     * This method is the getter for the End.
     * @return The End date and time for the Appointment.
     */
    public ZonedDateTime getEnd() { return End;}

    /**
     * This method is the getter for the Location.
     * @return The Location of the Appointment.
     */
    public String getLocation() { return Location; }

    /**
     * The getter for the Start.
     * @return The Start date and time for the Appointment.
     */
    public ZonedDateTime getStart() { return Start; }

    /**
     * The getter for the Title.
     * @return The Title for the Appointment.
     */
    public String getTitle() { return Title; }

    /**
     * The getter for the Type.
     * @return The Type of the Appointment.
     */
    public String getType() { return Type; }

    /**
     * The getter for the list of all Appointments.
     * @return The Observable List containing all Appointments.
     */
    public static ObservableList<Appointment> getAllAppointments() { return allAppointments; }

    /**
     * This method adds an Appointment to the AllAppointments Observable List.
     * @param newAppointment The appointment to be added to the Observable List.
     */
    public static void addAppointment(Appointment newAppointment) { allAppointments.add(newAppointment); }

    /**
     * This method removes an Appointment from the AllAppointments Observable List.
     * @param newAppointment The Appointment to be removed from the Observable List.
     */
    public static void deleteAppointment(Appointment newAppointment) { allAppointments.remove(newAppointment); }

    /**
     * This method checks what the next ID should be based on the highest ID value so far.
     * @return Highest ID of all Appointments.
     */
    public static int lastID() {
        int MaxID = 0;
        for (Appointment allAppointment : allAppointments) { MaxID = allAppointment.getAppointment_ID(); }
        return MaxID;
    }

    /**
     * This method returns the Customer associaed with an ID value.
     * @param ID The ID to search for.
     * @return The Customer associated with the ID.
     */
    public static Customer getCustomerByID(int ID) {
        for (int i = 0; i < Customer.getAllCustomers().size(); i++) {
            if (Customer.getAllCustomers().get(i).getCustomer_ID() == ID) {
                return Customer.getAllCustomers().get(i);
            }
        }
        return null;
    }

    /**
     * This method returns the User associated with and ID value.
     * @param ID The ID to search for.
     * @return The User associated with the ID.
     */
    public static User getUserByID(int ID) {
        for (int i = 0; i < User.getAllUsers().size(); i++) {
            if (User.getAllUsers().get(i).getUser_ID() == ID) {
                return User.getAllUsers().get(i);
            }
        }
        return null;
    }

    /**
     * This method validates if a string is a valid date and time in the format of the database.
     * @param date The String to be tested.
     * @return Whether the String is a valid date or not.
     */
    public static boolean dateValidator(String date) {
        try {
            LocalDate.parse(date, SetupDatabaseData.timeDateFormat);
        } catch (DateTimeParseException e){
            return false;
        }
        return true;
    }

    /**
     * This method checks if an Appointment is within working hours of the company.
     * @param timeStart The Start time and date of the Appointment.
     * @param timeEnd The End time and date of the Appointment.
     * @return Whether the Appointment is within working hours or not.
     */
    public static boolean inHoursValidator(ZonedDateTime timeStart, ZonedDateTime timeEnd) {
        ZonedDateTime estTimeStart = timeStart.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime estTimeEnd = timeEnd.withZoneSameInstant(ZoneId.of("America/New_York"));
        return estTimeStart.getHour() >= 8 && estTimeStart.getHour() <= 22 && estTimeEnd.getHour() >= 8 && estTimeEnd.getHour() <= 22 && DAYS.between(estTimeStart, estTimeEnd) < 1;
    }

    /**
     * This method validates that an Appointments Start is before it's End.
     * @param timeStart The Start of the Appointment.
     * @param timeEnd The End of the Appointment.
     * @return Whether the Start and End times are set up in a valid order.
     */
    public static boolean selfOverlapValidator(ZonedDateTime timeStart, ZonedDateTime timeEnd) { return timeEnd.isAfter(timeStart); }

    /**
     * This method validates that the Customer does not have any conflicting Appointments.
     * @param CustomerID The ID of the Customer to check.
     * @param startTime The Start Time of the Appointment.
     * @param endTime The End Time of the Appointment.
     * @param AppointmentID The ID of the Appointment. This makes sure it does not check for overlap with itself.
     * @return Returns true if the Customer has no conflicting Appointments.
     */
    public static boolean overlapValidator(int CustomerID, ZonedDateTime startTime, ZonedDateTime endTime, int AppointmentID) {
        for (int i = 0; i < Appointment.getAllAppointments().size(); i++) {
            if (CustomerID == Appointment.getAllAppointments().get(i).Customer_ID && AppointmentID != Appointment.getAllAppointments().get(i).getAppointment_ID()) {
                if (!(startTime.isAfter(Appointment.getAllAppointments().get(i).End) ||
                        endTime.isBefore(Appointment.getAllAppointments().get(i).Start) ||
                        startTime.isEqual(Appointment.getAllAppointments().get(i).Start) ||
                        endTime.isEqual(Appointment.getAllAppointments().get(i).End))) {
                    return false;
                }
            }
        }
        return true;
    }
}
