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
 * @author Kris
 */
public class LoadProductsFromShop implements Serializable {

    public LoadProductsFromShop() {
    }

    public ArrayList<ItemDTO> getAllShopProducts(int storeID ) {
        ArrayList<ItemDTO> itemsSummaries = new ArrayList<>();
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from tblstoreStock join tblitem on tblstorestock.ITEM_ID = tblitem.ITEM_ID where tblstoreStock.store_id = ?");
            stmt.setInt(1, storeID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemDTO item = new ItemDTO(rs.getInt("Item_ID"), rs.getString("itemName"), rs.getString("itemDesc"), rs.getString("itemBarcode"), rs.getString("itemPhoto"), rs.getDouble("itemPrice"),rs.getInt("qty"));
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
