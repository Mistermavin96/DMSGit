package ClassesAndFunctions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Customer {
    private int Customer_ID;
    private int Division_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private String Division_Name;
    private String Country_Name;
    private static final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

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

    public void setDivision_Name(String division_Name) {
        Division_Name = division_Name;
    }

    public String getDivision_Name() {
        return Division_Name;
    }

    public String getCountry_Name() {
        return Country_Name;
    }

    public void setCountry_Name(String country_Name) {
        Country_Name = country_Name;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public String getAddress() {
        return Address;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    @Override
    public String toString() {
      return Customer_Name;
    }

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    public static void deleteCustomer(Customer newCustomer) {
        allCustomers.remove(newCustomer);
    }

    public boolean hasAppointments(int customer_ID) {
        int i = 0;
        while (i < Appointment.getAllAppointments().size()) {
            if (customer_ID == Appointment.getAllAppointments().get(i).getCustomer_ID()) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static int lastID() {
        int i = 0;
        int MaxID = 0;
        while (i < allCustomers.size()) {
            MaxID = allCustomers.get(i).getCustomer_ID();
            i++;
        }
        return MaxID;
    }
}
