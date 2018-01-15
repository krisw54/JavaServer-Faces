package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "echoBean")
@RequestScoped
public class EchoBean
{
    private String name;
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
