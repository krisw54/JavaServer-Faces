package managedbean;

import dbWrappers.AddUser;
import dto.UserDTO;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import helpers.*;

@Named(value = "registration")
@RequestScoped
public class Registration implements Serializable
{
    private int id;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String postcode;
    private String mobile;
    private String landline;
    private String email;
    private String usename;
    private String password1;
    private String password2;

    public Registration() {
        
    }
    
    
    public String register() {
        boolean dataOK = false;

        if (password1.equals(password2))
        {
           AddUser add = new AddUser();
            UserDTO user = new UserDTO(id, firstName, lastName,  addressLine1, addressLine2,  addressLine3,  postcode,  mobile,  landline, email,  usename,  password1);
            dataOK = add.AddUserToDatabase(user);
        }

        if (dataOK)
        {
            return "login";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials are not correct"));
            return null;
        }
    }

    public Registration(int id, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, String postcode, String mobile, String landline, String email, String usename, String password1, String password2) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.postcode = postcode;
        this.mobile = mobile;
        this.landline = landline;
        this.email = email;
        this.usename = usename;
        this.password1 = password1;
        this.password2 = password2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    


    

}
