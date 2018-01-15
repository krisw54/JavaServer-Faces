package managedbean;

import boundary.UserUI;
import dto.CustomerDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "customer")
@SessionScoped
public class CustomerBean implements Serializable
{

    private UserUI userUI = new UserUI();
    private CustomerDTO customerDetails = null;
    private int totalCustomers = 0;

    public String fetchCustomerDetails(int CustID)
    {
        customerDetails = userUI.findCustomerById(CustID);
        return "viewCustomer";
    }

    public CustomerDTO getCustomerDetails()
    {
        return customerDetails;
    }

    public ArrayList<CustomerDTO> getCustomerSummaries()
    {
        ArrayList<CustomerDTO> customerSummaries = userUI.getCustomerSummaries();
        totalCustomers = customerSummaries.size();
        return customerSummaries;
    }

    public int getTotalCustomers()
    {
        return totalCustomers;
    }

    public void setCustomerDetails(CustomerDTO customerDetails)
    {
        this.customerDetails = customerDetails;
    }
}
