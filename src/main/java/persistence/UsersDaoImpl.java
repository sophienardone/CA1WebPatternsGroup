package persistence;

import musiclibrary.Users;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {


    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/musiclibrary";
    String username = "root";
    String password = "";
    @Override
    public List<Users> getAllUsers(){
        List<Users> usersList = new ArrayList<>();


        try{
            Class.forName(driver);

            try(Connection conn = DriverManager.getConnection(url, username, password)){
                try(PreparedStatement ps = conn.prepareStatement("SELECT * from Users")){
                    try(ResultSet rs = ps.executeQuery()){
                        while(rs.next()){
                            Users user = new Users(
                               rs.getInt("user_id"),
                               rs.getString("username"),
                               rs.getString("password"),
                               rs.getString("email"),
                                    rs.getString("credit_card_number"),
                                    rs.getDate("credit_card_expiry"),
                                    rs.getBoolean("is_active"),
                                    rs.getTimestamp("created_at")

                            );
                            usersList.add(user);
                        }
                    } catch (SQLException e) {
                        System.out.println(LocalDateTime.now() + ": SQLException occurred while running the query or processing the result.");
                        System.out.println("Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    System.out.println(LocalDateTime.now() + ": SQLException occurred while preparing the SQL statement.");
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while trying to connect to the database.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(LocalDateTime.now() + ": ClassNotFoundException occurred while trying to load the MySQL driver.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void addUser(Users user) throws SQLException {
        String query = "INSERT INTO users (username, password, email, credit_card_number, credit_card_expiry, is_active, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        if (user.getCreditCardNumber().length() != 16) {
            throw new IllegalArgumentException("Invalid credit card number.");
        }

        try {
            Class.forName(driver);


            try (Connection conn = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = conn.prepareStatement(query)) {


                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getCreditCardNumber());
                ps.setDate(5, new java.sql.Date(user.getCreditCardExpiry().getTime()));
                ps.setBoolean(6, true);
                ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

                ps.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}








