package persistence;

import musiclibrary.Users;

import java.util.List;

public interface UsersDao {
    void addUser(Users user) throws Exception;


    Users getUserByUsername(String username) throws Exception;

    boolean validateUser(String username, String password) throws Exception;

    List<Users> getAllUsers() throws Exception;

    void updateUser(Users user) throws Exception;

    void deleteUser(int userId) throws Exception;
}
