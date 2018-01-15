package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager
{
    public static Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Assignment1", "wmad", "wmad");
    }

    
}
