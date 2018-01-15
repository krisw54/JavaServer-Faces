package dbWrappers;

import managers.DatabaseManager;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import helpers.*;

/**
 *
 * @author Kris
 */
public class UserEdit {

    public UserEdit() {
        
    }

    public boolean SaveChangesToDatabase(UserDTO user) {
        boolean dataOK = false;
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt;
            if (user.getPassword().equals("47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=")) {
                stmt = conn.prepareStatement("UPDATE tblUser SET firstname =?, lastname=?, address1=?,address2=?,address3=?,postcode=?,mobile=?,landline=?,email=? WHERE user_id=?");
                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getAddressLine1());
                stmt.setString(4, user.getAddressLine2());
                stmt.setString(5, user.getAddressLine3());
                stmt.setString(6, user.getPostcode());
                stmt.setString(7, user.getMobile());
                stmt.setString(8, user.getLandline());
                stmt.setString(9, user.getEmail());
                stmt.setInt(10, user.getId());
            } else {
                stmt = conn.prepareStatement("UPDATE tblUser SET firstname =?, lastname=?, address1=?,address2=?,address3=?,postcode=?,mobile=?,landline=?,email=?,pwd=? WHERE user_id=?");
                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getAddressLine1());
                stmt.setString(4, user.getAddressLine2());
                stmt.setString(5, user.getAddressLine3());
                stmt.setString(6, user.getPostcode());
                stmt.setString(7, user.getMobile());
                stmt.setString(8, user.getLandline());
                stmt.setString(9, user.getEmail());
                stmt.setString(10, user.getPassword());
                stmt.setInt(11, user.getId());
            }

            int rows = stmt.executeUpdate();
            dataOK = rows == 1;
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataOK;
    }
}
