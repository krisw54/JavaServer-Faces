package gateway;

import dbWrappers.DeleteItemFromDB;
import dbWrappers.LoadProductsFromShop;
import dbWrappers.StockAllStores;
import dbWrappers.ViewItemDetails;
import dto.ItemDTO;
import java.util.ArrayList;

/**
 *
 * @author Kris
 */
public class ItemsGateway {

    public ArrayList<ItemDTO> find(int storeID) {
        LoadProductsFromShop items = new LoadProductsFromShop();
        return items.getAllShopProducts(storeID);
    }
    
    public ArrayList<ItemDTO> find() {
        StockAllStores stock = new StockAllStores();
        return stock.getAllShopProducts();
    }
    
    public ItemDTO find(int itemID, int storeID) {
        ViewItemDetails details = new ViewItemDetails();
        return details.getItemDetails(itemID, storeID);
    }

    public boolean insert(ItemDTO item) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean delete(int storeID, int itemID) {
        DeleteItemFromDB item = new DeleteItemFromDB();
        return item.DeleteItem(storeID, itemID);
    }

    public boolean update(ItemDTO item) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

}
