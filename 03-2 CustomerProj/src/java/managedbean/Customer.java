package managedbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "customer")
@RequestScoped
public class Customer
{

    private String customerDetails = "";

    public ArrayList<String> getCustomerSummaries()
    {
        ArrayList<String> customerSummaries = new ArrayList<>();
        try
        {
            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

            PreparedStatement stmt = conn.prepareStatement("SELECT Customer_ID, Name FROM Customer");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                String custSummary = rs.getInt("Customer_ID") + ": " + rs.getString("Name");
                customerSummaries.add(custSummary);
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return customerSummaries;
    }

    public String fetchCustomerDetails(int CustID)
    {
        customerDetails = "";
        try
        {
            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE Customer_ID = ?");
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = rs.getInt("Customer_ID") + ": " + rs.getString("Name")
                        + ": " + rs.getString("AddressLine1")
                        + ": " + rs.getString("AddressLine2")
                        + ": " + rs.getString("City")
                        + ": " + rs.getString("State")
                        + ": " + rs.getString("Zip");
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return "index";
    }

    public String getCustomerDetails()
    {
        return customerDetails;
    }
}
