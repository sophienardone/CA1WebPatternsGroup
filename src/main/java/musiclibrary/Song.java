package musiclibrary;

import java.util.Objects;

public class Song {

//    DROP TABLE IF EXISTS songs;
//    CREATE TABLE songs (
//            songID INT AUTO_INCREMENT PRIMARY KEY,
//            title VARCHAR(100) NOT NULL,
//    albumID INT NOT NULL,
//    duration INT NOT NULL,
//    FOREIGN KEY (albumID) REFERENCES albums(albumID) ON DELETE CASCADE
//);
    
    private int songID;
    private String title;
    private int albumID;
    private int duration;

    public Song(int songID, String title, int albumID, int duration) {
        this.songID = songID;
        this.title = title;
        this.albumID = albumID;
        this.duration = duration;
    }

    public int getSongID() {
        return songID;
    }

    public String getTitle() {
        return title;
    }

    public int getAlbumID() {
        return albumID;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songID == song.songID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(songID);
    }

    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", title='" + title + '\'' +
                ", albumID=" + albumID +
                ", duration=" + duration +
                '}';
    }
}
