package ClassesAndFunctions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

public class PreparedStatements {

    static PreparedStatement preparedStatement;

    public static boolean AddCustomer(Customer newCustomer) {
        try {
            DBConnect.openConnection();
            String query = "INSERT INTO Customers VALUES(?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = DBConnect.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, newCustomer.getCustomer_ID());
            preparedStatement.setString(2, newCustomer.getCustomer_Name());
            preparedStatement.setString(3, newCustomer.getAddress());
            preparedStatement.setString(4, newCustomer.getPostal_Code());
            preparedStatement.setString(5, newCustomer.getPhone());
            preparedStatement.setString(6, "2022-6-21 11:30:00");
            preparedStatement.setString(7, "AppointX");
            preparedStatement.setString(8, "2022-6-21 11:30:00");
            preparedStatement.setString(9, "AppointX");
            preparedStatement.setInt(10, newCustomer.getDivision_ID());
            preparedStatement.executeUpdate();
            DBConnect.closeConnection();
            return true;
        } catch(SQLException e) {
            System.out.println(e);
        }
        DBConnect.closeConnection();
        return false;
    }

    public static boolean ModifyCustomer(Customer modCustomer) {
        DBConnect.openConnection();
        DBConnect.closeConnection();
        return false;
    }

    public static boolean DeleteCustomer(Customer delCustomer) {
        DBConnect.openConnection();
        DBConnect.closeConnection();
        return false;
    }

    public static boolean AddAppointment(Appointment newAppointment) {
        DBConnect.openConnection();
        DBConnect.closeConnection();
        return false;
    }

    public static boolean ModifyAppointment(Appointment modAppointment) {
        DBConnect.openConnection();
        DBConnect.closeConnection();
        return false;
    }

    public static boolean DeleteAppointment(Appointment delAppointment) {
        DBConnect.openConnection();
        DBConnect.closeConnection();
        return false;
    }
}
