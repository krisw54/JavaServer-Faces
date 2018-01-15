package dbWrappers;

import managers.DatabaseManager;
import dto.OrderDTO;
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
public class PreviousOrders implements Serializable {
    private OrderDTO item;
    private ArrayList<OrderDTO> itemList = new ArrayList();

    public PreviousOrders() {
    }

    public ArrayList<OrderDTO> getPreviousOrders(int userID) {
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT tblorder.order_id, tbluser.user_id, dateoforder, timeoforder, tblitem.ITEM_ID, tblitem.ITEMNAME, tblitem.ITEMPRICE, tblorderline.QTY, (tblitem.ITEMPRICE * tblorderline.QTY) as total FROM tblorder left join tbluser ON tbluser.USER_ID = tblorder.USER_ID join tblorderline on tblorderline.ORDER_ID = tblorder.ORDER_ID join tblitem on tblorderline.ITEM_ID = tblitem.ITEM_ID WHERE tbluser.user_id=? order by tblorder.ORDER_ID");
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                item = new OrderDTO(rs.getInt("Order_ID"),
                        rs.getString("dateOfOrder"), 
                        rs.getString("timeOfOrder"), 
                        rs.getInt("item_id"), 
                        rs.getString("itemName"), 
                        rs.getDouble("itemPrice"), 
                        rs.getInt("qty"),
                        rs.getDouble("Total"));
                itemList.add(item);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
        }
        return itemList;
    }

    public OrderDTO getItem()
    {
        return item;
    }

    public void setItem(OrderDTO item)
    {
        this.item = item;
    }

    public ArrayList<OrderDTO> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<OrderDTO> itemList)
    {
        this.itemList = itemList;
    }
    
    

    
}
