package gateway;

import dbWrappers.AddUser;
import dbWrappers.CheckOut;
import dbWrappers.LoadUsers;
import dbWrappers.PreviousOrders;
import dbWrappers.UserEdit;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class OrderGateway {

    public ArrayList<OrderDTO> find(int userID) {
        PreviousOrders o = new PreviousOrders();
        return o.getPreviousOrders(userID);
    }

    public boolean insert(int userID, ArrayList<ItemDTO> cartItems) {
        CheckOut co = new CheckOut();
        return co.AddCartToDatabase(userID, cartItems);
    }

    public boolean delete(int userID) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean update(UserDTO user) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

}
