package managed_bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "userUnprotected")
@RequestScoped
public class UserUnprotected
{
    /*
    * The database interactions in this class are unprotected
    * because they are using java.sql.Statement objects instead
    * of java.sql.PreparedStatement objects.
    */

    private String name;
    private String postcode;

    public String getName()
    {
        return name;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String storeNameInDatabase()
    {
        try
        {
            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/WMAD_demo", "user1", "user1");
            
            String sqlStr = "INSERT INTO UserDemo VALUES (' " + name + "', '" + postcode + "')";
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(sqlStr);
            
            stmt.close();            
            con.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return "viewTime";
    }

    public String findPostcode()
    {
        try
        {
            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/WMAD_demo", "user1", "user1");
            
            String sqlStr = "SELECT postcode FROM UserDemo WHERE username = '" + name + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStr);
            
            String postCodeStr = "";
            
            while (rs.next())
            {
                postCodeStr += rs.getString("postcode") + ", ";
                System.out.println(postCodeStr);
            }
            postcode = postCodeStr.isEmpty() ? "" : postCodeStr.substring(0, postCodeStr.length()-2);
            
            rs.close();
            stmt.close();            
            con.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return "injectionExample";
    }
}
