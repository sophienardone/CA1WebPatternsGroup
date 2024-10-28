package musiclibrary;

import java.util.Date;
import java.util.Objects;

public class Rating {


//    DROP TABLE IF EXISTS ratings;
//    CREATE TABLE ratings (
//            ratingID INT AUTO_INCREMENT PRIMARY KEY,
//            userID INT NOT NULL,
//            songID INT NOT NULL,
//            ratingValue INT NOT NULL,
//            ratingDate DATE NOT NULL,
//            FOREIGN KEY (userID) REFERENCES users(user_id) ON DELETE CASCADE,
//    FOREIGN KEY (songID) REFERENCES songs(songID) ON DELETE CASCADE,
//    CHECK (ratingValue BETWEEN 1 AND 5)
//);

    private int ratingID;
    private int userID;
    private int songID;
    private int ratingValue;
    private Date ratingDate;

    public Rating(int ratingID, int userID, int songID, int ratingValue, Date ratingDate) {
        this.ratingID = ratingID;
        this.userID = userID;
        this.songID = songID;
        this.ratingValue = ratingValue;
        this.ratingDate = ratingDate;
    }

    public int getRatingID() {
        return ratingID;
    }

    public int getUserID() {
        return userID;
    }

    public int getSongID() {
        return songID;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return ratingID == rating.ratingID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ratingID);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingID=" + ratingID +
                ", userID=" + userID +
                ", songID=" + songID +
                ", ratingValue=" + ratingValue +
                ", ratingDate=" + ratingDate +
                '}';
    }
}
