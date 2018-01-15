package managedbean;

import dbWrappers.LoadProductsFromShop;
import dbWrappers.StockAllStores;
import dto.ItemDTO;
import dto.UserDTO;
import gateway.ItemsGateway;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import helpers.Cart;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Kris
 */
@Named(value = "shop")
@SessionScoped
public class ViewShop implements Serializable
{

    private int itemID;
    private int totalItems;
    private ArrayList<ItemDTO> itemDTOs;
    private int storeID;
    private int qty;
    private Cart cart;
    private String search;
    private int storeIDManager = 0;
    private ItemsGateway iGateway = new ItemsGateway();
    
    @Inject
    ItemDetail details;

    public ViewShop()
    {
        cart = new Cart();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("cart", cart);

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("userDetails");
        storeIDManager = (user.getStoreID());

        if (user.getUserType().equalsIgnoreCase("branch manager"))
        {
            storeID = user.getStoreID();
        }
        else if (user.getUserType().equalsIgnoreCase("area manager"))
        {
            storeID = 1;
        }
        else
        {
            String store = (String) session.getAttribute("storeID");
            storeID = Integer.parseInt(store);
        }

        itemDTOs = iGateway.find(storeID);
        totalItems = itemDTOs.size();

        if (user.getUserType()
                .equalsIgnoreCase("area manager"))
        {
            itemDTOs = iGateway.find();
        }
    }

    public String getSearch()
    {
        return search;
    }

    public void setSearch(String search)
    {
        this.search = search;
    }

    public int getItemID()
    {
        return itemID;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }

    public int getTotalItems()
    {
        return totalItems;
    }

    public void setTotalItems(int totalItems)
    {
        this.totalItems = totalItems;
    }

    public ArrayList<ItemDTO> getItemDTO()
    {
        return itemDTOs;
    }

    public void setItemDTO(ArrayList<ItemDTO> itemDTOs)
    {
        this.itemDTOs = itemDTOs;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public void setStoreID(int storeID)
    {
        this.storeID = storeID;
    }

    public String search()
    {
        String message = "Error: Nothing Found For: " + search;
        for (int i = 0; i < itemDTOs.size(); i++)
        {
            if (itemDTOs.get(i).getItemName().equalsIgnoreCase(search))
            {
                message = "Item Found: " + search;
                return details.itemDetail(itemDTOs.get(i).getItemID(), 1);
            }
        }
        FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage(message));
        return null;
    }
    
    public void deleteItem(int storeID, int itemID) {
        iGateway.delete(storeID, itemID);
    }
}
