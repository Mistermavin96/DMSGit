package Classes;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * This class runs statements for initial setup from the database.
 */
public class SetupDatabaseData {
    public static DateTimeFormatter timeDateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    public static DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));
    public static void setup() {
        DBConnect.openConnection();
        try (Statement stmt = DBConnect.getConnection().createStatement()) {

            ResultSet userSet = stmt.executeQuery("SELECT * FROM users");
            while (userSet.next()) {
                User tempUser = new User(
                        userSet.getInt(1),
                        userSet.getString(2),
                        userSet.getString(3));
                User.addUser(tempUser);
            }
            userSet.close();

            ResultSet firstLevelDivisionSet = stmt.executeQuery("SELECT * FROM first_level_divisions");
            while (firstLevelDivisionSet.next()) {
                FirstLevelDivision tempFirstLevelDivision = new FirstLevelDivision(
                        firstLevelDivisionSet.getInt(1),
                        firstLevelDivisionSet.getString(2),
                        firstLevelDivisionSet.getInt(7));
                FirstLevelDivision.addFirstLevelDivision(tempFirstLevelDivision);
            }
            firstLevelDivisionSet.close();

            ResultSet countrySet = stmt.executeQuery("SELECT * FROM countries");
            while (countrySet.next()) {
                Country tempCountry = new Country(
                        countrySet.getInt(1),
                        countrySet.getString(2)
                );
                Country.addCountry(tempCountry);
            }
            countrySet.close();

            ResultSet contactSet = stmt.executeQuery("SELECT * FROM contacts");
                while (contactSet.next()) {
                    Contact tempContact = new Contact(
                            contactSet.getInt(1),
                            contactSet.getString(2),
                            contactSet.getString(3));
                    Contact.addContact(tempContact);
                }
                contactSet.close();

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
                        ZonedDateTime.of(LocalDateTime.parse(appointmentSet.getString(6), timeDateFormat), ZoneId.of("UTC")),
                        ZonedDateTime.of(LocalDateTime.parse(appointmentSet.getString(7), timeDateFormat), ZoneId.of("UTC")),
                        appointmentSet.getInt(12),
                        appointmentSet.getInt(13),
                        appointmentSet.getInt(14));
                Appointment.addAppointment(tempAppointment);
            }
            appointmentSet.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        DBConnect.closeConnection();
        Total.setAllTotals();
    }
}