package managedbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class User implements Serializable
{

    private String name = "Graham";
    private int age = 0;

    public User()
    {
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String user_name)
    {
        this.name = user_name;
    }

    public void validateAge(FacesContext context, UIComponent component, Object value)
    {
        String message = "";
        int ageToCheck = (Integer)value;
        
        if (ageToCheck < 1 || ageToCheck > 100)
        {
            ((UIInput)component).setValid(false);
            message = "The age must be in the range 1-100";
            context.addMessage(component.getClientId(), new FacesMessage(message));
        }
    }

    public void validateName(FacesContext context, UIComponent component, Object value)
    {
        String message = "";
        String nameToCheck = (String)value;
        
        if (nameToCheck.equalsIgnoreCase("Jimmy"))
        {
            ((UIInput)component).setValid(false);
            message = "The name cannot be 'Jimmy'";
            context.addMessage(component.getClientId(), new FacesMessage(message));
        }
    }
}
