package helpers;

import dto.ItemDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Kris
 */
@Named(value = "cart")
@SessionScoped
public class Cart implements Serializable {

    private ArrayList<ItemDTO> cart = new ArrayList();
    private double totalCartCost;
    private int totalItems;

    public Cart() {

    }

    public ArrayList<ItemDTO> getCart() {
        return cart;
    }

    public void setCart(ArrayList<ItemDTO> cart) {
        this.cart = cart;
    }

    public void addItem(ItemDTO item) {
        cart.add(item);
    }

    public double getTotalCartCost() {
        return totalCartCost;
    }

    public void setTotalCartCost(double totalCartCost) {
        this.totalCartCost = totalCartCost;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void checkOutCart() {
        System.out.println(cart.size());

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null
                || !(obj instanceof Cart)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Cart cm = (Cart) obj;
        return this.totalItems == cm.totalItems;
    }

    @Override
    public int hashCode() {
        return String.valueOf(totalItems).hashCode() + totalItems;
    }

}
