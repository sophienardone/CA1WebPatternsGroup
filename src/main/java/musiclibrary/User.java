package musiclibrary;

import java.util.Date;

public class User {

//CREATE TABLE users (
//                       user_id INT AUTO_INCREMENT PRIMARY KEY,
//                       username VARCHAR(50) NOT NULL UNIQUE,
//                       password VARCHAR(255) NOT NULL,
//                       email VARCHAR(100) NOT NULL UNIQUE,
//                       credit_card_number VARCHAR(16) NOT NULL,
//                       credit_card_expiry DATE NOT NULL,
//                       is_active BOOLEAN DEFAULT TRUE,
//                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);


    private int userId;
    private String username;
    private String password;
    private String email;
    private String creditCardNumber;
    private Date creditCardExpiry;
    private boolean isActive;
    private Date createdAt;


    public User(int userId, String username, String password, String email, String creditCardNumber, Date creditCardExpiry, boolean isActive, Date createdAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return userId == user.userId;
    }


    @Override
    public int hashCode() {
        return userId;
    }


    @Override
    public String toString() {
        return "musiclibrary.Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }


    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public boolean isActive() {
        return isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }



}
