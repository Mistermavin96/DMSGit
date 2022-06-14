package DMS;

import java.sql.ResultSet;
import java.sql.Statement;

public class SetupDatabaseData {
    public static void setup() {
        DBConnect.openConnection();

        try (Statement stmt = DBConnect.connection.createStatement()) {
            ResultSet customerSet = stmt.executeQuery("SELECT * FROM Customers");
            while (customerSet.next()) {
                Customer tempCustomer = new Customer(
                        customerSet.getInt(1),
                        customerSet.getInt(10),
                        customerSet.getString(2),
                        customerSet.getString(3),
                        customerSet.getString(4),
                        customerSet.getString(5));
                Customer.addCustomer(tempCustomer);
            }
            customerSet.close();

            ResultSet appointmentSet = stmt.executeQuery("SELECT * FROM Appointments");
            while (appointmentSet.next()) {
                Appointment tempAppointment = new Appointment(
                        appointmentSet.getInt(1),
                        appointmentSet.getString(2),
                        appointmentSet.getString(3),
                        appointmentSet.getString(4),
                        appointmentSet.getString(5),
                        appointmentSet.getString(6),
                        appointmentSet.getString(7),
                        appointmentSet.getInt(12),
                        appointmentSet.getInt(13),
                        appointmentSet.getInt(14));
                Appointment.addAppointment(tempAppointment);
            }
            appointmentSet.close();

            ResultSet countrySet = stmt.executeQuery("SELECT * FROM countries");
            while (countrySet.next()) {
                Country tempCountry = new Country(
                        countrySet.getInt(1),
                        countrySet.getString(2)
                );
                Country.addCountry(tempCountry);
            }
            countrySet.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        DBConnect.closeConnection();
    }
}
