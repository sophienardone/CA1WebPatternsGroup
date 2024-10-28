package musiclibrary;

import java.util.Date;
import java.util.Objects;

public class Album {


//    CREATE TABLE albums (
//        albumID INT AUTO_INCREMENT PRIMARY KEY,
//        title VARCHAR(100) NOT NULL,
//    artistID INT NOT NULL,
//    releaseDate DATE NOT NULL,
//    genre VARCHAR(50) NOT NULL,
//    label VARCHAR(50),
//    duration INT NOT NULL,
//    price DECIMAL(5, 2) NOT NULL,
//    FOREIGN KEY (artistID) REFERENCES artists(artistID) ON DELETE CASCADE
//);


    private int albumID;
    private String title;
    private int artistID;
    private Date releaseDate;
    private String genre;
    private String label;
    private int duration;
    private double price;





    public Album(int albumID, String title, int artistID, Date releaseDate, String genre, String label, int duration, double price) {
        this.albumID = albumID;
        this.title = title;
        this.artistID = artistID;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.label = label;
        this.duration = duration;
        this.price = price;
    }

    public int getAlbumID() {
        return albumID;
    }



    public String getTitle() {
        return title;
    }



    public int getArtistID() {
        return artistID;
    }



    public Date getReleaseDate() {
        return releaseDate;
    }



    public String getGenre() {
        return genre;
    }



    public String getLabel() {
        return label;
    }



    public int getDuration() {
        return duration;
    }



    public double getPrice() {
        return price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album albums = (Album) o;
        return albumID == albums.albumID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(albumID);
    }

    @Override
    public String toString() {
        return "Albums{" +
                "albumID=" + albumID +
                ", title='" + title + '\'' +
                ", artistID=" + artistID +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", label='" + label + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
