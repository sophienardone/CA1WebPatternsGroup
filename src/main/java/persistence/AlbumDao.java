package persistence;

import musiclibrary.Album;

import java.util.List;

public interface AlbumDao {
    // Adds a new album to the database
    void addAlbum(Album album) throws Exception;

    // Retrieves a specific album by its albumID
    Album getAlbumById(int albumID) throws Exception;

    // Retrieves all albums by a specific artistID
    List<Album> getAlbumsByArtistId(int artistID) throws Exception;

}
