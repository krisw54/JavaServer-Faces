package managedbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User implements Serializable
{

    private String firstName = "Graham";
    private String lastName = "Mansfield";

    public User()
    {
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String fName)
    {
        this.firstName = fName;
        //System.out.println("First name set");
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lName)
    {
        this.lastName = lName;
        //System.out.println("Last name set");
    }
}
