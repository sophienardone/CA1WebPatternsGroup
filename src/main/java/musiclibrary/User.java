package musiclibrary;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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


    public User(String username, String password, String email, String creditCardNumber, Date creditCardExpiry, boolean isActive, Date createdAt) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.isActive = isActive;
        this.createdAt = createdAt;
        userId = 0;
    }






}
