package Classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;

/**
 * This class has all prepared statements used to modify the database.
 */
public class PreparedStatements {

    static PreparedStatement preparedStatement;

    /**
     * This method adds a Customer entry to the database.
     * @param newCustomer The Customer to be added.
     */
    public static void AddCustomer(Customer newCustomer) {
        try {
            DBConnect.openConnection();
            String query = "INSERT INTO Customers VALUES(?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, newCustomer.getCustomer_ID());
            preparedStatement.setString(2, newCustomer.getCustomer_Name());
            preparedStatement.setString(3, newCustomer.getAddress());
            preparedStatement.setString(4, newCustomer.getPostal_Code());
            preparedStatement.setString(5, newCustomer.getPhone());
            preparedStatement.setString(6, SetupDatabaseData.toStringFormatter.format(Instant.now()));
            preparedStatement.setString(7, "AppointX");
            preparedStatement.setString(8, SetupDatabaseData.toStringFormatter.format(Instant.now()));
            preparedStatement.setString(9, "AppointX");
            preparedStatement.setInt(10, newCustomer.getDivision_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
        } catch(SQLException e) {
            System.out.println(e);
            DBConnect.closeConnection();
        }
    }

    /**
     * This method modifies a Customer entry already in the database.
     * @param modCustomer The Customer with information to be set in the database.
     */
    public static void ModifyCustomer(Customer modCustomer) {
        try {
            DBConnect.openConnection();
            String query = "UPDATE Customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setString(1, modCustomer.getCustomer_Name());
            preparedStatement.setString(2, modCustomer.getAddress());
            preparedStatement.setString(3, modCustomer.getPostal_Code());
            preparedStatement.setString(4, modCustomer.getPhone());
            preparedStatement.setString(5, SetupDatabaseData.toStringFormatter.format(Instant.now()));
            preparedStatement.setString(6, "AppointX");
            preparedStatement.setInt(7, modCustomer.getDivision_ID());
            preparedStatement.setInt(8, modCustomer.getCustomer_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
        } catch (SQLException e){
            System.out.println(e);
            DBConnect.closeConnection();
        }
    }

    /**
     * This method deletes a Customer entry in a database.
     * @param delCustomer The Customer to be deleted.
     */
    public static void DeleteCustomer(Customer delCustomer) {
        try {
            DBConnect.openConnection();
            String query = "DELETE FROM Customers WHERE Customer_ID = ?";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, delCustomer.getCustomer_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
        } catch(SQLException e) {
            System.out.println(e);
            DBConnect.closeConnection();
        }
    }

    /**
     * This method adds an Appointment entry to the database.
     * @param newAppointment The Appointment to be added to the database.
     */
    public static void AddAppointment(Appointment newAppointment) {
        try {
            DBConnect.openConnection();
            String query = "INSERT INTO Appointments VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, newAppointment.getAppointment_ID());
            preparedStatement.setString(2, newAppointment.getTitle());
            preparedStatement.setString(3, newAppointment.getDescription());
            preparedStatement.setString(4, newAppointment.getLocation());
            preparedStatement.setString(5, newAppointment.getType());
            preparedStatement.setString(6, newAppointment.getStart().withZoneSameInstant(ZoneId.of("Etc/UTC")).format(SetupDatabaseData.toStringFormatter));
            preparedStatement.setString(7, newAppointment.getEnd().withZoneSameInstant(ZoneId.of("Etc/UTC")).format(SetupDatabaseData.toStringFormatter));
            preparedStatement.setString(8,SetupDatabaseData.toStringFormatter.format(Instant.now()));
            preparedStatement.setString(9, "AppointX");
            preparedStatement.setString(10, SetupDatabaseData.toStringFormatter.format(Instant.now()));
            preparedStatement.setString(11, "AppointX");
            preparedStatement.setInt(12, newAppointment.getContact_ID());
            preparedStatement.setInt(13, newAppointment.getUser_ID());
            preparedStatement.setInt(14, newAppointment.getContact_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
        } catch(SQLException e) {
            DBConnect.closeConnection();
            System.out.println(e);
        }
    }

    /**
     * This method modifies an Appointment entry already in the database.
     * @param modAppointment The Appointment with the information to modify the database.
     */
    public static void ModifyAppointment(Appointment modAppointment) {
        try {
            DBConnect.openConnection();
            String query = "UPDATE Appointments SET  Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setString(1, modAppointment.getTitle());
            preparedStatement.setString(2, modAppointment.getDescription());
            preparedStatement.setString(3, modAppointment.getLocation());
            preparedStatement.setString(4, modAppointment.getType());
            preparedStatement.setString(5, modAppointment.getStart().withZoneSameInstant(ZoneId.of("Etc/UTC")).format(SetupDatabaseData.toStringFormatter));
            preparedStatement.setString(6, modAppointment.getEnd().withZoneSameInstant(ZoneId.of("Etc/UTC")).format(SetupDatabaseData.toStringFormatter));
            preparedStatement.setString(7, SetupDatabaseData.toStringFormatter.format(Instant.now()));
            preparedStatement.setString(8, "AppointX");
            preparedStatement.setInt(9, modAppointment.getContact_ID());
            preparedStatement.setInt(10, modAppointment.getUser_ID());
            preparedStatement.setInt(11, modAppointment.getContact_ID());
            preparedStatement.setInt(12, modAppointment.getAppointment_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
            DBConnect.closeConnection();
        }
    }

    /**
     * This method deletes an Appointment entry from the database.
     * @param delAppointment The Appointment to be deleted from the database.
     */
    public static void DeleteAppointment(Appointment delAppointment) {
        try {
            DBConnect.openConnection();
            String query = "DELETE FROM Appointments WHERE Appointment_ID = ?";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, delAppointment.getAppointment_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}