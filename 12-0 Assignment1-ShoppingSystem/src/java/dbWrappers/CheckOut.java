package dbWrappers;

import managers.DatabaseManager;
import helpers.DateTimeFormatter;
import dto.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kris
 */
public class CheckOut {

    private DateTimeFormatter dt = new DateTimeFormatter();
    private int lastOrder;
    private ArrayList<ItemDTO> cartItems;
    private UpdateStoreStock stock = new UpdateStoreStock();
    private int storeID;

    public CheckOut() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            String store = (String) session.getAttribute("storeID");
            storeID = Integer.parseInt(store);
        } catch (Exception e) {
            System.out.println(e);
            storeID = 1;
        }
    }

    public boolean AddCartToDatabase(int userID, ArrayList<ItemDTO> cart) {
        boolean dataOK = false;
        cartItems = cart;
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblOrder (user_id, dateOfOrder, timeOfOrder) VALUES (?, ?, ?)");

            stmt.setInt(1, userID);
            stmt.setString(2, dt.formatDateNow());
            stmt.setString(3, dt.formatTimeNow());

            int rows = stmt.executeUpdate();
            dataOK = rows == 1;
            stmt.close();
            conn.close();

            AddCartToDatabaseOrder();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataOK;
    }

    private boolean AddCartToDatabaseOrder() {
        boolean dataOK = false;
        try {
            Connection conn = DatabaseManager.getConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblOrder");

            rs.afterLast();
            if (rs.previous()) {
                lastOrder = rs.getInt("Order_ID");

            }

            System.out.println(lastOrder);
            AddCartItemsToDatabase();

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataOK;

    }

    public boolean AddCartItemsToDatabase() {
        boolean dataOK = false;
        try {
            Connection conn = DatabaseManager.getConnection();
            for (int i = 0; i < cartItems.size(); i++) {
                PreparedStatement stmt = conn.prepareStatement("INSERT into tblOrderLine (ITEM_ID, ORDER_ID, qty) VALUES (?, ?, ?)");
                stmt.setInt(1, cartItems.get(i).getItemID());
                stmt.setInt(2, lastOrder);
                stmt.setInt(3, cartItems.get(i).getQtyReq());

                int rows = stmt.executeUpdate();
                dataOK = rows == 1;
                stmt.close();
                stock.UpdateStockDB(cartItems.get(i).getItemID(), storeID, cartItems.get(i).getQtyReq());
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataOK;
    }

}
