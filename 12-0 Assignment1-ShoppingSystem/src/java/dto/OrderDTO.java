package dto;

import helpers.PriceMaker;
import java.io.Serializable;

/**
 *
 * @author w028006g
 */
public class OrderDTO implements Serializable {

    private int orderID;
    private String dateOfOrder;
    private String timeOfOrder;
    private int itemID;
    private String itemName;
    private double itemPrice;
    private int qty;
    private double total;

    public OrderDTO(int orderID, String dateOfOrder, String timeOfOrder, int itemID, String itemName, double itemPrice, int qty, double total) {
        this.orderID = orderID;
        this.dateOfOrder = dateOfOrder;
        this.timeOfOrder = timeOfOrder;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.qty = qty;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(String timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        PriceMaker m = new PriceMaker();
        return m.setDoubleToCurrency(total);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null
                || !(obj instanceof OrderDTO)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        OrderDTO order= (OrderDTO) obj;
        return this.orderID == order.getOrderID()
                && this.dateOfOrder.equals(order.getDateOfOrder());
    }

    @Override
    public int hashCode() {
        return dateOfOrder.hashCode() + orderID;
    }
}
