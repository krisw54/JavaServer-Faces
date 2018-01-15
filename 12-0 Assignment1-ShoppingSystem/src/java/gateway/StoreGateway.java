package gateway;

import dbWrappers.ListStores;
import dbWrappers.UpdateStoreStock;
import dto.ItemDTO;
import dto.StoreDTO;
import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class StoreGateway {

    public ArrayList<StoreDTO> find() {
        ListStores listStores = new ListStores();
        return listStores.LoadStoresFromDatabase();
    }

    public boolean insert(StoreDTO store) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean delete(int storeID) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean update(ArrayList<ItemDTO> cartItems, int storeID) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

}
