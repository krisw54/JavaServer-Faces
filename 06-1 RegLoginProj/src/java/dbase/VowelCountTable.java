package dbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class VowelCountTable
{

    public Map<String, Integer> getTotalVowels() throws SQLException
    {
        Connection conn;
        conn = DbManager.getConnection();
        Map<String, Integer> totalVowelCount = getTotalVowels(DbManager.getConnection());
        conn.close();
        return totalVowelCount;
    }

    public Map<String, Integer> getTotalVowels(Connection conn) throws SQLException
    {
        Map<String, Integer> totalVowelCount = new HashMap<>(5);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VowelCount");
        ResultSet rs = stmt.executeQuery();

        while (rs.next())
        {
            String vStr = rs.getString("Vowel").toUpperCase();
            totalVowelCount.put(vStr, rs.getInt("Vcount"));
        }

        stmt.close();
        return totalVowelCount;
    }

    public Map<String, Integer> updateTotalVowels(Map<String, Integer> vowelCount) throws SQLException
    {
        Connection conn;
        conn = DbManager.getConnection();

        Map<String, Integer> totalVowelCount = getTotalVowels(conn);

        PreparedStatement stmt = conn.prepareStatement("UPDATE VowelCount SET Vcount = ? WHERE Vowel = ?");
        for (String key : vowelCount.keySet())
        {
            int count = vowelCount.get(key);
            if (count > 0)
            {
                int newCount = totalVowelCount.get(key) + count;
                totalVowelCount.replace(key, newCount);
                stmt.setInt(1, newCount);
                stmt.setString(2, key);
                stmt.executeUpdate();
            }
        }
        stmt.close();

        conn.close();

        return totalVowelCount;
    }
}
