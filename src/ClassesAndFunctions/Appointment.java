package ClassesAndFunctions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private String Start;
    private String End;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private static final ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, String Start, String End, int Customer_ID, int User_ID, int Contact_ID) {
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
    }

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setEnd(String end) {
        End = end;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setStart(String start) {
        Start = start;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
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

    public String getEnd() {
        return End;
    }

    public String getLocation() {
        return Location;
    }

    public String getStart() {
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
}
