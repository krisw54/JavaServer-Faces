package managed_bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "userProtected")
@RequestScoped
public class UserProtected
{

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
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO UserDemo VALUES (?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, postcode);
            int rows = stmt.executeUpdate();
            
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
            
            PreparedStatement stmt = con.prepareStatement("SELECT postcode FROM UserDemo WHERE username = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            
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
        return "queryExample";
    }
}
