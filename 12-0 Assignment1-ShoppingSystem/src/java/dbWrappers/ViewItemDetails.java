package dbWrappers;

import managers.DatabaseManager;
import dto.ItemDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kris
 */
public class ViewItemDetails implements Serializable {
    private ItemDTO item;

    public ViewItemDetails() {
    }

    public ItemDTO getItemDetails(int itemID, int storeID) {
        
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT tblstorestock.item_id,qty,itemname,itemdesc,itembarcode,itemprice,itemphoto FROM tblstorestock left join tblitem ON tblitem.item_id = tblstorestock.item_id where store_id=? and tblitem.item_ID=?");
            stmt.setInt(1, storeID);
            stmt.setInt(2, itemID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                item = new ItemDTO(rs.getInt("Item_ID"), rs.getString("itemName"), rs.getString("itemDesc"), rs.getString("itemBarcode"), rs.getString("itemPhoto"), rs.getDouble("itemPrice"), rs.getInt("qty"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
        }
        return item;
    }

}
