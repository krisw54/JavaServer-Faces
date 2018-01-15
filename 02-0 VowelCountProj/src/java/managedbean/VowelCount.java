package managedbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "vowelCount")
@RequestScoped
public class VowelCount
{

    private String text;
    private Map<String,Integer> vowelCount = new HashMap<>(5);
    private Map<String,Integer> totalVowelCount = new HashMap<>(5);
    
    public VowelCount()
    {
        vowelCount.put("A", 0);
        vowelCount.put("E", 0);
        vowelCount.put("I", 0);
        vowelCount.put("O", 0);
        vowelCount.put("U", 0);
    }

    public String getText()
    {
        return text;
    }

    public int getNumberOfA()
    {
        return vowelCount.get("A");
    }

    public int getNumberOfE()
    {
        return vowelCount.get("E");
    }

    public int getNumberOfI()
    {
        return vowelCount.get("I");
    }

    public int getNumberOfO()
    {
        return vowelCount.get("O");
    }

    public int getNumberOfU()
    {
        return vowelCount.get("U");
    }

    public int getTotalA()
    {
        return totalVowelCount.get("A");
    }

    public int getTotalE()
    {
        return totalVowelCount.get("E");
    }

    public int getTotalI()
    {
        return totalVowelCount.get("I");
    }

    public int getTotalO()
    {
        return totalVowelCount.get("O");
    }

    public int getTotalU()
    {
        return totalVowelCount.get("U");
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String countVowels()
    {
        for (char c : text.toUpperCase().toCharArray())
        {
            if (vowelCount.containsKey(""+c))
            {
                vowelCount.replace(""+c, vowelCount.get(""+c)+1);
            }
        }
        updateTotalVowels();
        return "vowelCount";
    }

    private Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
        return DriverManager.getConnection("jdbc:derby://localhost:1527/WMAD_demo", "user1", "user1");
    }

    private void getTotalVowels(Connection conn) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VowelCount");
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            String vStr = rs.getString("Vowel").toUpperCase();
            totalVowelCount.put(vStr, rs.getInt("Vcount"));
        }

        stmt.close();
    }

    private void updateTotalVowels()
    {
        Connection conn;
        try
        {
            conn = getConnection();
            getTotalVowels(conn);
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE VowelCount SET Vcount = ? WHERE Vowel = ?");
            for (String key : vowelCount.keySet())
            {
                int count = vowelCount.get(key);
                if (count > 0)
                {
                    int newCount = totalVowelCount.get(key)+count;
                    totalVowelCount.replace(key, newCount);
                    stmt.setInt(1, newCount);
                    stmt.setString(2, key);
                    stmt.executeUpdate();
                }
            }
            stmt.close();

            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

    }
}
