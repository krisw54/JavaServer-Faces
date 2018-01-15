package dto;

import helpers.PasswordHash;
import java.io.Serializable;

public class UserDTO implements Serializable {

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
    private String password;
    private String userType = "";
    private int storeID;
    private boolean credentialsOK = false;
    private PasswordHash hash = new PasswordHash();

    public UserDTO(int id, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, String postcode, String mobile, String landline, String email, String usename, String password, String userType) {
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
        this.password = password;
        this.userType = userType;
    }

    public UserDTO(int id, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, String postcode, String mobile, String landline, String email, String usename, String password) {
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
        this.password = hash.hashThePassword(password);
    }

    public UserDTO(int id, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3, String postcode, String mobile, String landline, String email, String password) {
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
        this.password = hash.hashThePassword(password);
    }

    public UserDTO(String firstName, String lastName, String email, String usename, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.usename = usename;
        this.password = password;
    }

    public UserDTO(int id, String firstName, String lastName, String email, String usename, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.usename = usename;
        this.password = password;
        this.id = id;
    }

    public UserDTO(String username, String password) {
        this.usename = username;
        PasswordHash hash = new PasswordHash();
        this.password = hash.hashThePassword(password);
    }

    public UserDTO() {
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordHash hash = new PasswordHash();
        this.password = hash.hashThePassword(password);
    }

    public boolean isCredentialsOK() {
        return credentialsOK;
    }

    public void setCredentialsOK(boolean credentialsOK) {
        this.credentialsOK = credentialsOK;
    }

}
