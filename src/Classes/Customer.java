package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * This class is for the Customer objects, which handles the data associated with customers and provides methods for interacting with them.
 */
public class Customer {
    private final int Customer_ID;
    private int Division_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private final String Division_Name;
    private final String Country_Name;
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * This method is the constructor for the Customer class, and determines Country Name and Division Name based on input ID for Division.
     * @param Customer_ID The unique identifier for the Customer.
     * @param Division_ID The unique identifier for the Division associated with the Customer.
     * @param Customer_Name The Name of the Customer.
     * @param Address The Address where the Customer lives.
     * @param Postal_Code The Postal Code of the Customer.
     * @param Phone The Phone Number for the Customer
     */
    public Customer(int Customer_ID, int Division_ID, String Customer_Name, String Address, String Postal_Code, String Phone) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Division_ID = Division_ID;
        this.Phone = Phone;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Division_Name = Objects.requireNonNull(FirstLevelDivision.getDivisionbyDivisionID(Division_ID)).getDivision_Name();
        this.Country_Name = Objects.requireNonNull(FirstLevelDivision.getCountryByDivisionId(Division_ID)).getCountry_Name();
    }

    /**
     * This method is the getter for the Division Name associated with the Customer, mostly used for tables.
     * @return The Name of the Division associated with the Customer.
     */
    public String getDivision_Name() { return Division_Name; }

    /**
     * This method is the getter for the Country Name associated with the Customer, mostly used for tables.
     * @return The Name of the Country associated with the Customer.
     */
    public String getCountry_Name() { return Country_Name; }

    /**
     * This method is the getter for the unique identifier for the Customer, mostly used for tables.
     * @return The ID of the Customer.
     */
    public int getCustomerID() { return Customer_ID; }

    /**
     * This method is the setter for the Address of the Customer.
     * @param address The Address associated with the Customer.
     */
    public void setAddress(String address) { Address = address; }

    /**
     * This method is the setter for the Name of the Customer.
     * @param customer_Name The Name of the Customer.
     */
    public void setCustomer_Name(String customer_Name) { Customer_Name = customer_Name; }

    /**
     * This method is the setter for the unique identifier of the Division associated with the Customer.
     * @param division_ID The Division ID for the Customer.
     */
    public void setDivision_ID(int division_ID) { Division_ID = division_ID; }

    /**
     * This method is the setter for the Phone Number of the Customer.
     * @param phone The Phone Number of the Customer.
     */
    public void setPhone(String phone) { Phone = phone; }

    /**
     * This method is setter for the Postal Code where the Customer lives.
     * @param postal_Code The Postal Code for the Customer.
     */
    public void setPostal_Code(String postal_Code) { Postal_Code = postal_Code; }

    /**
     * This method is the getter for the unique identifier for the Customer.
     * @return The ID of the Customer.
     */
    public int getCustomer_ID() { return Customer_ID; }

    /**
     * This method is the getter for the unique identifier for the Division associated with the Customer.
     * @return The ID of the Division where the Customer lives.
     */
    public int getDivision_ID() { return Division_ID; }

    /**
     * This method is the getter for the Address where the Customer lives.
     * @return The Address of the Customer.
     */
    public String getAddress() { return Address; }

    /**
     * This method is the getter for the Name of the Customer.
     * @return The Name of the Customer.
     */
    public String getCustomer_Name() { return Customer_Name; }

    /**
     * This method is the getter for the Phone Number of the Customer.
     * @return The Phone Number of the Customer.
     */
    public String getPhone() { return Phone; }

    /**
     * This method is the getter for the Postal Code where the Customer lives.
     * @return The Postal Code of the Customer.
     */
    public String getPostal_Code() { return Postal_Code; }

    /**
     * This method is an override for the default ToString method, setting it to the Customer Name, for use in combo boxes.
     * @return The Name of the Customer.
     */
    @Override public String toString() { return Customer_Name; }

    /**
     * This method is a getter for the observable list of all Customers.
     * @return The observable list of all Customers.
     */
    public static ObservableList<Customer> getAllCustomers() { return allCustomers; }

    /**
     * This method adds a customer to the list of all Customers.
     * @param newCustomer The Customer to be added to the list.
     */
    public static void addCustomer(Customer newCustomer) { allCustomers.add(newCustomer); }

    /**
     * This method removes a Customer from the list of all Customers.
     * @param delCustomer The Customer to be deleted.
     */
    public static void deleteCustomer(Customer delCustomer) { allCustomers.remove(delCustomer); }

    /**
     * This method checks if a Customer has any Appointments based on customer ID.
     * @param customer_ID The ID of the Customer to check.
     * @return Returns true if an Appointment was found associated with the Customer, otherwise returns false.
     */
    public boolean hasAppointments(int customer_ID) {
        for (int i = 0; i < Appointment.getAllAppointments().size(); i++) {
            if (customer_ID == Appointment.getAllAppointments().get(i).getCustomer_ID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks what the highest ID associaed with any Customer is.
     * @return The highest ID of any Customer.
     */
    public static int lastID() {
        int MaxID = 0;
        for (Customer allCustomer : allCustomers) {
            MaxID = allCustomer.getCustomer_ID();
        }
        return MaxID;
    }
}