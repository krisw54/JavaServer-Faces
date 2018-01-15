package dbWrappers;

import managers.DatabaseManager;
import dto.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author w028006g
 */
public class UpdateStoreStock
{

    public boolean UpdateStockDB(int itemID, int storeID, int qty)
    {
        boolean dataOK = false;
        int dbQty = 0;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from tblstoreStock where store_id = ? and item_id = ?");
            stmt.setInt(1, storeID);
            stmt.setInt(2, itemID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               dbQty = rs.getInt("qty");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
        }
        
        try
        {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("update tblstorestock set qty = ? where store_id = ? and item_id = ?");
            stmt.setInt(1, (dbQty - qty));
            stmt.setInt(2, storeID);
            stmt.setInt(3, itemID);

            int rows = stmt.executeUpdate();
            dataOK = rows == 1;
            stmt.close();

            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return dataOK;

    }
}
