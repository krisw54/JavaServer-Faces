package managedbean;

import dbWrappers.ViewItemDetails;
import dto.ItemDTO;
import gateway.ItemsGateway;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import helpers.Cart;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Kris
 */
@Named(value = "idetail")
@SessionScoped
public class ItemDetail implements Serializable
{

    private int itemID;
    private int storeID;
    private ItemDTO itemDTO;
    private int qty;
    private ItemsGateway iGateway = new ItemsGateway();

    public ItemDetail()
    {

    }

    public String itemDetail(int itemID, int storeID)
    {
        itemDTO = iGateway.find(itemID, storeID);
        return "viewItemDetails";
    }

    public String addToCart()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        int item = itemDTO.getStockLevel();

        if (qty > item)
        {
            FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Error: Not enough in stock to buy sorry!"));
            return null;
        }
        else
        {
            if (cart.getCart().isEmpty())
            {
                FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Information: Item Added!"));
                itemDTO.setQtyReq(qty);
                cart.addItem(itemDTO);
            }
            else
            {
                boolean isThere = false;
                for (int i = 0; i < cart.getCart().size(); i++)
                {
                    if (cart.getCart().get(i).getItemID() == itemDTO.getItemID())
                    {
                        isThere = true;
                        cart.getCart().get(i).setQtyReq(qty + cart.getCart().get(i).getQtyReq());
                        if (cart.getCart().get(i).getQtyReq() > item)
                        {
                            cart.getCart().get(i).setQtyReq(item);
                            FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Error: Not enough in stock! The most you can order is: " + item));
                        }
                    }
                }
                if (!isThere)
                {
                    FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Information: Item Added!"));
                    itemDTO.setQtyReq(qty);
                    cart.addItem(itemDTO);
                }

            }
        }
        return "shop";
    }

    public String addToCart(int qty, int itemId, int storeID)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        ViewItemDetails details = new ViewItemDetails();
        itemDTO = details.getItemDetails(itemId, storeID);
        int item = itemDTO.getStockLevel();

        if (qty > item)
        {
            FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Error: Not enough in stock to buy sorry!"));
            return null;
        }
        else
        {
            if (cart.getCart().isEmpty())
            {
                FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Information: Item Added!"));
                itemDTO.setQtyReq(qty);
                cart.addItem(itemDTO);
            }
            else
            {
                boolean isThere = false;
                for (int i = 0; i < cart.getCart().size(); i++)
                {
                    if (cart.getCart().get(i).getItemID() == itemDTO.getItemID())
                    {
                        isThere = true;
                        cart.getCart().get(i).setQtyReq(qty + cart.getCart().get(i).getQtyReq());
                        if (cart.getCart().get(i).getQtyReq() > item)
                        {
                            cart.getCart().get(i).setQtyReq(item);
                            FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Error: Not enough in stock! The most you can order is: " + item));
                        }
                    }
                }
                if (!isThere)
                {
                    FacesContext.getCurrentInstance().addMessage("itemDetail:qty", new FacesMessage("Information: Item Added!"));
                    itemDTO.setQtyReq(qty);
                    cart.addItem(itemDTO);
                }

            }
        }
        return "shop";
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public ItemDTO getItemDTO()
    {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO)
    {
        this.itemDTO = itemDTO;
    }

    public int getItemID()
    {
        return itemID;
    }

    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public void setStoreID(int storeID)
    {
        this.storeID = storeID;
    }

}
