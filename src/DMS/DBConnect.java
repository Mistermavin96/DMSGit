package DMS;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ip = "//localhost/";
    private static final String db = "client_schedule";
    private static final String URL = protocol + vendor + ip + db + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    public static void openConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("Connection successful!");
        } catch (Exception e) { System.out.println("Error:" + e.getMessage()); }
    }

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (Exception e) { System.out.println("Error:" + e.getMessage()); }
    }
}