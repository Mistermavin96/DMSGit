package ClassesAndFunctions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contact {
    int Contact_ID;
    String Contact_Name;
    String Contact_Email;
    public final static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public Contact(int contact_ID, String contact_Name, String contact_Email) {
        this.Contact_ID = contact_ID;
        this.Contact_Name = contact_Name;
        this.Contact_Email = contact_Email;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public String getContact_Email() {
        return Contact_Email;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public void setContact_Email(String contact_Email) {
        Contact_Email = contact_Email;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public static void addContact(Contact newContact) { allContacts.add(newContact); }

    public static ObservableList<Contact> getAllContacts() { return allContacts; }

    @Override
    public String toString() {
        return Contact_Name;
    }

    public static Contact getContactByID(int ContactID) {
        int i = 0;
        while (i < getAllContacts().size()) {
            if (getAllContacts().get(i).Contact_ID == ContactID) {
                return getAllContacts().get(i);
            }
            i++;
        }
        return null;
    }

    public ObservableList<Appointment> getAppointmentsByContact() {
        int i = 0;
        ObservableList<Appointment> returnList = FXCollections.observableArrayList();
        while (i < Appointment.getAllAppointments().size()) {
            if (Appointment.getAllAppointments().get(i).getContact_ID() == this.getContact_ID()) {
                returnList.add(Appointment.getAllAppointments().get(i));
            }
            i++;
        }

        return returnList;
    }

}
