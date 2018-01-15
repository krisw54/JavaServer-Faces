package managedbean;

/**
 *
 * @author Kris
 */
import dbWrappers.UserEdit;
import dto.UserDTO;
import gateway.UserGateway;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import helpers.*;
import javax.servlet.http.HttpSession;

@Named(value = "updateUser")
@RequestScoped
public class UpdateUser implements Serializable {

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
    private String password1 = null;
    private String password2 = null;
    private UserGateway uGateway = new UserGateway();

    public UpdateUser() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserDTO user = (UserDTO) session.getAttribute("userDetails");
        firstName = user.getFirstName();
        lastName = user.getLastName();
        addressLine1 = user.getAddressLine1();
        addressLine2 = user.getAddressLine2();
        addressLine3 = user.getAddressLine3();
        postcode = user.getPostcode();
        email = user.getEmail();
        mobile = user.getMobile();
        landline = user.getLandline();
        id = user.getId();
    }

    public String saveChanges() {
        boolean dataOK = false;

        if (password1.equals(password2) || password1.isEmpty() && password2.isEmpty()) {
            UserDTO user = new UserDTO(id, firstName, lastName, addressLine1, addressLine2, addressLine3, postcode, mobile, landline, email, password1);
            dataOK = uGateway.update(user);
        }

        if (dataOK) {
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credentials are not correct"));
            return null;
        }
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
