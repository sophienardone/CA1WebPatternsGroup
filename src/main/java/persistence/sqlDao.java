package persistence;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class sqlDao {
    private String databaseName = "";

    public sqlDao(String databaseName) {
        this.databaseName = databaseName;
    }
    public Connection getConnection() {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/musiclibrary" + databaseName;
            String username = "root";
            String password = "";
            Connection conn = null;
            try {
                Class.forName(driver);
                 conn = DriverManager.getConnection(url, username, password);

            } catch (ClassNotFoundException e) {
                System.out.println(LocalDateTime.now() + "failed to find Driver ");
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        return conn;
    }

   public void freeConnection(Connection conn) {
        try{
            conn.close();
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() +);;
        }
   }



}
