package managedbean;

import dto.ItemDTO;
import helpers.PriceMaker;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import helpers.Cart;

/**
 *
 * @author Kris
 */
@Named(value = "viewCart")
@RequestScoped
public class ViewCart {
    
    private ArrayList<ItemDTO> cartItems;
    private int totalItems;
    private int qty;
    private double totalCost;
    
    public ViewCart() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");
        cartItems = cart.getCart();
    }
    
    public ArrayList<ItemDTO> getCartItems() {
        return cartItems;
    }
    
    public void setCartItems(ArrayList<ItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
    
    public int getTotalItems() {
        totalItems = cartItems.size();
        return totalItems;
    }
    
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    
    public int getQty() {
        return qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }
    
    public double getTotalCost() {
        double cost = 0;
        PriceMaker price = new PriceMaker();
        for (int i = 0; i < cartItems.size(); i++) {
            cost = cost + cartItems.get(i).getPrice() * cartItems.get(i).getQtyReq();
        }
        totalCost = price.setDoubleToCurrency(cost);
        return totalCost;
    }
    
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    public void removeItem(ItemDTO item) {
        cartItems.remove(item);
    }
    
}
