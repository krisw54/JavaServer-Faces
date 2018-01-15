package gateway;

import dbWrappers.UserEdit;
import dbWrappers.LoadUsers;
import dbWrappers.AddUser;
import dto.UserDTO;

/**
 *
 * @author Kris
 */
public class UserGateway {
    
    public UserDTO find(UserDTO user){
        LoadUsers findUser = new LoadUsers();
        return findUser.LoadUserFromDatabase(user);
    }
    
    public boolean insert(UserDTO user) {
        AddUser addUser = new AddUser();
        return addUser.AddUserToDatabase(user);
    }

    public boolean delete(int userID) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }

    public boolean update(UserDTO user) {
        UserEdit uEdit = new UserEdit();
        return uEdit.SaveChangesToDatabase(user);
    }

}
