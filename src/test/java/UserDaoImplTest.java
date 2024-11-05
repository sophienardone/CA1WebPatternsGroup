import musiclibrary.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import persistence.UserDaoImpl;
import persistence.UsersDao;
import persistence.sqlDao;
import java.util.Date;
import java.util.List;


public class UserDaoImplTest {
    private UserDaoImpl userDaoImpl;
    private sqlDao sqlDao;


    public UserDaoImplTest() {
        sqlDao = new sqlDao("musiclibrary_test");
     //   userDaoImpl = new UserDaoImpl(sqlDao);
    }


    @Test
    public void testAddUser() {

        UsersDao userDao = new UserDaoImpl("musiclibrary_test");
        try {
            User user = new User(0, "jamesm20", "whitesquare123", "jamesm@gmail.com", "1234567890123456", new Date(), true, new Date());
            userDao.addUser(user);

            List<User> users = userDao.getAllUsers();
            boolean userExists = false;
            for (User u : users) {
                if (u.getUsername().equals("jamesm20")) {
                    userExists = true;
                    break;
                }
            }

            assertTrue(userExists, "The user should be added successfully.");
        } catch (Exception e) {
            fail("Exception occurred during addUser test: " + e.getMessage());
        }
    }

    @Test
    public void testValidateUser() {

        UsersDao userDao = new UserDaoImpl("musiclibrary_test");

        try {
            User user = new User(0, "abdigodone23", "pword234", "abdig2@gmail.com", "6543210987654321", new Date(), true, new Date());
            userDao.addUser(user);

            boolean isValid = userDao.validateUser("abdigodone23", "pword234");
            assertTrue(isValid, "User validation should succeed");
        } catch (Exception e) {
            fail("Exception occurred during validateUser test: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllUsers() {

        UsersDao userDao = new UserDaoImpl("musiclibrary_test");

        try {
            User user = new User(0, "sophienardone", "goodpassword456", "sophie.nardone20@gmail.com", "0987654321098765", new Date(), true, new Date());
            userDao.addUser(user);

            List<User> users = userDao.getAllUsers();
            boolean userFound = false;
            for (User u : users) {
                if (u.getUsername().equals("sophienardone")) {
                    userFound = true;
                    break;
                }
            }

            assertTrue(userFound, "The list of users should include recently added user.");
        } catch (Exception e) {
            fail("Exception occurred during getAllUsers test: " + e.getMessage());
        }
    }
}








