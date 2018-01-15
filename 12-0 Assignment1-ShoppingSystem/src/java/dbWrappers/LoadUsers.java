package dbWrappers;

import managers.DatabaseManager;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import helpers.*;
import java.sql.ResultSet;

/**
 *
 * @author Kris
 */
public class LoadUsers {

    public LoadUsers() {
    }
    
    
    public UserDTO LoadUserFromDatabase(UserDTO user) {
        boolean credentialsOK = false;
         try
        {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tblUser WHERE uName = ?");
            stmt.setString(1, user.getUsename());
            ResultSet rs = stmt.executeQuery();

            credentialsOK = rs.next() && rs.getString("pwd").equals(user.getPassword());
            
            if(credentialsOK) {
                user.setCredentialsOK(true);
                user.setId(rs.getInt("User_ID"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setAddressLine1(rs.getString("address1"));
                user.setAddressLine2(rs.getString("address2"));
                user.setAddressLine3(rs.getString("address3"));
                user.setPostcode(rs.getString("postcode"));
                user.setMobile(rs.getString("mobile"));
                user.setLandline(rs.getString("landline"));
                user.setEmail(rs.getString("email"));
                user.setUserType(rs.getString("UserType"));
                user.setStoreID(rs.getInt("store"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return user;
    }       
 }
