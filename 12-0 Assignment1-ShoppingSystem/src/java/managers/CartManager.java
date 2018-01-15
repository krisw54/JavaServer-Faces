package managers;

import helpers.Cart;
import dbWrappers.CheckOut;
import dto.ItemDTO;
import dto.UserDTO;
import gateway.OrderGateway;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kris
 */
@Named(value = "cartFunctions")
@RequestScoped
public class CartManager implements Serializable
{

    private ArrayList<ItemDTO> cart;
    private double totalCartCost;
    private int totalItems;
    private ArrayList<ItemDTO> cartItems = new ArrayList();
    private UserDTO user;
    private OrderGateway oGateway = new OrderGateway();

    public CartManager()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        cartItems = cart.getCart();
        UserDTO userdto = (UserDTO) session.getAttribute("userDetails");
        user = userdto;
    }

    public ArrayList<ItemDTO> getCart()
    {
        return cart;
    }

    public void setCart(ArrayList<ItemDTO> cart)
    {
        this.cart = cart;
    }

    public void addItem(ItemDTO item)
    {
        cart.add(item);
    }

    public double getTotalCartCost()
    {
        return totalCartCost;
    }

    public void setTotalCartCost(double totalCartCost)
    {
        this.totalCartCost = totalCartCost;
    }

    public int getTotalItems()
    {
        return totalItems;
    }

    public void setTotalItems(int totalItems)
    {
        this.totalItems = totalItems;
    }

    public void removeItem(String id)
    {
        for (int i = 0; i < cartItems.size(); i++)
        {
            if (cartItems.get(i).getUid().equals(id))
            {
                cartItems.remove(i);
            }
        }
    }

    public void clearCart()
    {
        cartItems.clear();
    }

    public void checkOutCart()
    {
        oGateway.insert(user.getId(), cartItems);
        cartItems.clear();
    }

}
