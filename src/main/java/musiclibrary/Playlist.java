package musiclibrary;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    //
//    CREATE TABLE playlists (
//            playlistID INT AUTO_INCREMENT PRIMARY KEY,
//            userID INT NOT NULL,
//            name VARCHAR(100) NOT NULL,
//    description VARCHAR(255),
//    creationDate DATE NOT NULL,
//    FOREIGN KEY (userID) REFERENCES users(user_id) ON DELETE CASCADE
//);
    private int playlistID;
    private int userID;
    private String name;
    private String description;
    private LocalDateTime creationDate;


    public Playlist(int userID, String name, String description, LocalDateTime creationDate) {
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        playlistID = 0;
    }



}
