package managed_bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "user")
@RequestScoped
public class User
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

