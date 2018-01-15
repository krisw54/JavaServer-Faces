package managedbean;

import dto.CustomerDTO;
import dto.DiscountDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named(value = "discountRates")
@ApplicationScoped
public class DiscountRates
{

    private final ArrayList<DiscountDTO> rates = new ArrayList<>();

    public DiscountRates()
    {
        try
        {
            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Discount_Code");
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                DiscountDTO rate = new DiscountDTO(rs.getString("Discount_Code"), rs.getDouble("Rate"));
                rates.add(rate);
            }

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
    }

    public SelectItem[] getRateList()
    {
        SelectItem[] items = new SelectItem[rates.size()];

        for (int i = 0; i < rates.size(); i++)
        {
            DiscountDTO rate = rates.get(i);
            items[i] = new SelectItem(rate);
        }
        
        return items;
    }
}
