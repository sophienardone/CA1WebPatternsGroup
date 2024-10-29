package persistence;

import musiclibrary.Song;


public interface SongDao {

    // Adds a new song to the database
    void addSong(Song song) throws Exception;


    // Deletes a song by its songID
    void deleteSong(int songID) throws Exception;
}
