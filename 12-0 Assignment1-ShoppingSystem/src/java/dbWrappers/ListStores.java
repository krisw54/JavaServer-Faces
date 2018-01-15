package dbWrappers;

import managers.DatabaseManager;
import dto.StoreDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class ListStores {

    public ListStores() {
    }

    public ArrayList<StoreDTO> LoadStoresFromDatabase() {
        ArrayList<StoreDTO> stores = new ArrayList<>();
        StoreDTO storeDetails = null;
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblstore");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                storeDetails = new StoreDTO(
                        rs.getInt("STORE_ID"),
                        rs.getString("storeName"),
                        rs.getString("storeAddress"),
                        rs.getString("storeTel"),
                        rs.getInt("storeManID"));
                stores.add(storeDetails);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return stores;
    }

}
