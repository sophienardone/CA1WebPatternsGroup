package musiclibrary;

import java.util.Date;
import java.util.Objects;

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
    private Date creationDate;


    public Playlist(int playlistID, int userID, String name, String description, Date creationDate) {
        this.playlistID = playlistID;
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlists = (Playlist) o;
        return playlistID == playlists.playlistID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playlistID);
    }

    @Override
    public String toString() {
        return "Playlists{" +
                "playlistID=" + playlistID +
                ", userID=" + userID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
