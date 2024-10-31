package persistence;

import musiclibrary.User;

import java.util.List;

public interface UsersDao {
    boolean addUser(User user) throws Exception;

//    Users getUserByUsername(String username) throws Exception;

    boolean validateUser(String username, String password) throws Exception;

    List<User> getAllUsers() throws Exception;

//    void updateUser(Users user) throws Exception;
//
//    void deleteUser(int userId) throws Exception;
}
