package dbWrappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import managers.DatabaseManager;

/**
 *
 * @author Kris
 */
public class DeleteItemFromDB {

    public boolean DeleteItem(int storeID, int itemID) {
        boolean dataOK = false;
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tblStoreStock WHERE item_id=? and store_id=?");
            stmt.setInt(1, itemID);
            stmt.setInt(2, storeID);

            int rows = stmt.executeUpdate();
            dataOK = rows == 1;
            stmt.close();

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataOK;
    }

}
