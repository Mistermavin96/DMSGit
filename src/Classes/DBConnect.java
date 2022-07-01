package Classes;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class contains methods for controlling the connection to the database.
 */
public class DBConnect {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ip = "//localhost/";
    private static final String db = "client_schedule";
    private static final String URL = protocol + vendor + ip + db + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection connection;

    /**
     * This method opens a connection with the database server.
     */
    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) { System.out.println("Error:" + e.getMessage()); }
    }

    /**
     * This method safely closes the connection to the database.
     */
    public static void closeConnection(){
        try {
            connection.close();
        } catch (Exception e) { System.out.println("Error:" + e.getMessage()); }
    }

    /**
     * This method is a getter for the connection for usage to communicate with the database.
     * @return The connection with the database.
     */
    public static Connection getConnection() {
        return connection;
    }
}