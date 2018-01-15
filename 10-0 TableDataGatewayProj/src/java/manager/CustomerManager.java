package manager;

import dto.CustomerDTO;
import gateway.CustomerGateway;
import java.util.ArrayList;

public class CustomerManager
{

    private CustomerGateway gateway = new CustomerGateway();
    
    public CustomerDTO findCustomer(int CustID)
    {
        return gateway.find(CustID);
    }

    public CustomerDTO findCustomer(String name, String addressLine1, String zipCode)
    {
        return gateway.find(name, addressLine1, zipCode);
    }

    public ArrayList<CustomerDTO> getCustomerSummaries()
    {
        return gateway.findAllSummaries();
    }

    public boolean insertCustomer(CustomerDTO cust)
    {
        return gateway.insert(cust);
    }

}
