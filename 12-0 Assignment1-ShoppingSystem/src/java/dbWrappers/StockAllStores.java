package dbWrappers;

import managers.DatabaseManager;
import dto.ItemDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author w028006g
 */
public class StockAllStores implements Serializable {

    public StockAllStores() {
    }

    public ArrayList<ItemDTO> getAllShopProducts() {
        ArrayList<ItemDTO> itemsSummaries = new ArrayList<>();
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from tblstoreStock join tblitem on tblstorestock.ITEM_ID = tblitem.ITEM_ID");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemDTO item = new ItemDTO(rs.getInt("Item_ID"), rs.getString("itemName"), rs.getString("itemDesc"), rs.getString("itemBarcode"), rs.getString("itemPhoto"), rs.getDouble("itemPrice"),rs.getInt("qty"), rs.getInt("store_id"));
                itemsSummaries.add(item);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
        }
        return itemsSummaries;
    }

}

