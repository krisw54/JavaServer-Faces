package managed_bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "user1")
@RequestScoped
public class User1
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
        storeNameInDatabase(name);
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    private void storeNameInDatabase(String name)
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
    }
}
