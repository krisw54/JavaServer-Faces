package managers;

import dbWrappers.LoadUsers;
import dto.UserDTO;
import gateway.UserGateway;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import helpers.*;
import java.util.Map;
import javax.faces.context.ExternalContext;

@Named(value = "user")
@SessionScoped
public class UserManager implements Serializable
{

    private String usename;
    private String password;
    private String name;
    private boolean credentialsOK = false;
    private UserDTO user = new UserDTO();
    private boolean branchManager = false;
    private boolean areaManager = false;
    private boolean customer = false;
    private UserGateway uGateway = new UserGateway();

    public UserManager()
    {
    }

    public String checkCredentials()
    {

        user = new UserDTO(usename, password);
        uGateway.find(user);
        credentialsOK = user.isCredentialsOK();
        name = user.getFirstName();
        if (user.getUserType().equalsIgnoreCase("area manager"))
        {
            areaManager = true;
        } else if (user.getUserType().equalsIgnoreCase("branch manager"))
        {
            branchManager = true;
        } else {
            customer = true;
        }
        password = "";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("userDetails", user);

        if (credentialsOK)
        {
            return "index";
        }
        else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct"));
            return null;
        }

    }

    public boolean isBranchManager()
    {
        return branchManager;
    }

    public void setBranchManager(boolean branchManager)
    {
        this.branchManager = branchManager;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }
    

    public boolean isAreaManager()
    {
        return areaManager;
    }

    public void setAreaManager(boolean areaManager)
    {
        this.areaManager = areaManager;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public UserDTO getUser()
    {
        return user;
    }

    public void setUser(UserDTO user)
    {
        this.user = user;
    }

    public boolean isCredentialsOK()
    {
        return credentialsOK;
    }

    public void setCredentialsOK(boolean credentialsOK)
    {
        this.credentialsOK = credentialsOK;
    }

    private void clearCredentials()
    {
        this.usename = "";
        this.password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public boolean credentialsAreOK()
    {
        return credentialsOK;
    }

    public String logOff()
    {
        clearCredentials();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String getUsename()
    {
        return usename;
    }

    public void setUsename(String usename)
    {
        this.usename = usename;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
