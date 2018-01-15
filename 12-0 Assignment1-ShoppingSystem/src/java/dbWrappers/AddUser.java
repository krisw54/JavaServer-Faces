package dbWrappers;

import managers.DatabaseManager;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import helpers.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kris
 */
public class AddUser {

    public AddUser() {
    }
   
    
    public boolean AddUserToDatabase(UserDTO user) {
        boolean dataOK = false;
        DateTimeFormatter dtf = new DateTimeFormatter();
        try
            {
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO tblUser (firstname, lastname, address1,address2,address3,postcode,mobile,landline,email,uname,pwd,datecreated,timecreated,usertype,store) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getAddressLine1());
                stmt.setString(4, user.getAddressLine2());
                stmt.setString(5, user.getAddressLine3());
                stmt.setString(6, user.getPostcode());
                stmt.setString(7, user.getMobile());
                stmt.setString(8, user.getLandline());
                stmt.setString(9, user.getEmail());
                stmt.setString(10, user.getUsename());
                stmt.setString(11, user.getPassword());
                stmt.setString(12, dtf.formatDateNow());
                stmt.setString(13, dtf.formatTimeNow());
                stmt.setString(14, "User");  
                stmt.setInt(15, 0);
                int rows = stmt.executeUpdate();
                dataOK = rows == 1;
                stmt.close();
                conn.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        return dataOK;
    }
   
 }
