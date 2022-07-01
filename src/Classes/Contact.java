package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates a Contract object, which is used to utilize the contacts in the database. This class also contains methods for handling Contacts.
 */
public class Contact {
    int Contact_ID;
    String Contact_Name;
    String Contact_Email;
    public final static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * This method is the constructor for the class.
     * @param contact_ID The unique identifier for the class.
     * @param contact_Name The name to be used for the contact.
     * @param contact_Email The email associated with the account.
     */
    public Contact(int contact_ID, String contact_Name, String contact_Email) {
        this.Contact_ID = contact_ID;
        this.Contact_Name = contact_Name;
        this.Contact_Email = contact_Email;
    }

    /**
     * This method is a getter for the Contact ID.
     * @return The ID associated with the contact.
     */
    public int getContact_ID() { return Contact_ID; }

    /**
     * This method is a getter for the Name of the Contact.
     * @return The name for the Contact.
     */
    public String getContact_Name() { return Contact_Name; }

    /**
     * This method adds a Contact to the list of all contacts.
     * @param newContact The Contact that is to be added to the list.
     */
    public static void addContact(Contact newContact) { allContacts.add(newContact); }

    /**
     * This method returns an observable list containing all Contacts.
     * @return The observable list of all Contacts.
     */
    public static ObservableList<Contact> getAllContacts() { return allContacts; }

    /**
     * This method overrides the basic ToString to the Name of the contact instead, for usage in combo boxes.
     * @return The Name of the contact.
     */
    @Override public String toString() { return Contact_Name; }

    /**
     * This method takes an ID value and returns the first contact it finds that matches.
     * @param ContactID The ID to match a Contact to.
     * @return Any Contact associated with the ID, returns null if none was found.
     */
    public static Contact getContactByID(int ContactID) {
        for (int i = 0; i < getAllContacts().size(); i++) {
            if (getAllContacts().get(i).Contact_ID == ContactID) {
                return getAllContacts().get(i);
            }
        }
        return null;
    }

    /**
     * This method finds all Apppointments assigned to the current contact, and returns them in an observable list.
     * @return The full list of Appointments associated with the current Contact.
     */
    public ObservableList<Appointment> getAppointmentsByContact() {
        ObservableList<Appointment> returnList = FXCollections.observableArrayList();
        for (int i = 0; i < Appointment.getAllAppointments().size(); i++) {
            if (Appointment.getAllAppointments().get(i).getContact_ID() == this.getContact_ID()) {
                returnList.add(Appointment.getAllAppointments().get(i));
            }
        }
        return returnList;
    }
}
