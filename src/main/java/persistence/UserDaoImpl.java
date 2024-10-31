package persistence;

import musiclibrary.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UsersDao {

    private final sqlDao sqlDao;


    /**
     * Constructs a UserDaoImpl with the specified sqlDao.
     *
     * @param sqlDao the sqlDao used to obtain database connections
     */
    public UserDaoImpl(sqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }


    /**
     * Retrieves all users from the database.
     *
     * @return a List of User objects representing all users in the database.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sqlquery = "SELECT * FROM Users";


        try (Connection connection = sqlDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("credit_card_number"),
                        resultSet.getDate("credit_card_expiry"),
                        resultSet.getBoolean("is_active"),
                        resultSet.getTimestamp("created_at")
                );
                usersList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while running the query or processing the result.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return usersList;
    }


    /**
     * Adds a new user to the database.
     *
     * @param user the User object containing the user information to be added.
     * @throws SQLException             if a database access error occurs.
     * @throws IllegalArgumentException if the credit card number is invalid (not 16 digits).
     */
    @Override
    public boolean addUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, email, credit_card_number, credit_card_expiry, is_active, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        if (user.getCreditCardNumber().length() != 16) {
            throw new IllegalArgumentException("Invalid credit card number.");
        }


        try (Connection connection = sqlDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getCreditCardNumber());
            preparedStatement.setDate(5, new java.sql.Date(user.getCreditCardExpiry().getTime()));
            preparedStatement.setBoolean(6, true);
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * Validates a user based on username and password.
     *
     * @param username the username of the user to be validated.
     * @param password the password of the user to be validated.
     * @return true if the user is valid (exists in the database), false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean validateUser(String username, String password) throws SQLException {
        boolean isValid = false;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";


        try (Connection connection = sqlDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}









