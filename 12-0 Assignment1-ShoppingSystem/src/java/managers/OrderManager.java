package managers;

import dbWrappers.PreviousOrders;
import dto.OrderDTO;
import dto.UserDTO;
import gateway.OrderGateway;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author w028006g
 */
@Named(value = "previousOrders")
@RequestScoped
public class OrderManager
{
    private ArrayList<OrderDTO> orders;
    private OrderGateway oGateway = new OrderGateway();

    public OrderManager()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserDTO userdto = (UserDTO) session.getAttribute("userDetails");
        orders = oGateway.find(userdto.getId());
    }

    public ArrayList<OrderDTO> getOrders()
    {
        return orders;
    }

    public void setOrders(ArrayList<OrderDTO> orders)
    {
        this.orders = orders;
    }
    
    public int getOrderSize() {
        int s = orders.size();
        return s;
    }
}
