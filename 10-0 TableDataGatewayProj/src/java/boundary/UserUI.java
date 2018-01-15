package boundary;

import dto.CustomerDTO;
import java.util.ArrayList;
import manager.CustomerManager;

public class UserUI
{
    private final CustomerManager custMgr = new CustomerManager();
    
    public CustomerDTO addCustomer(CustomerDTO cust)
    {
        if (custMgr.insertCustomer(cust))
        {
            return custMgr.findCustomer(cust.getName(), cust.getAddressLine1(), cust.getZipCode());
        }
        return null;
    }

    public CustomerDTO findCustomerById(int CustID)
    {
        return custMgr.findCustomer(CustID);
    }
    
    public ArrayList<CustomerDTO> getCustomerSummaries()
    {
        return custMgr.getCustomerSummaries();
    }

    
}
