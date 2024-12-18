package persistence;

import musiclibrary.Album;

import java.util.List;

public interface AlbumDao {
    // Adds a new album to the database
    int addAlbum(Album album) ;

    // Retrieves a specific album by its albumID
    Album getAlbumById(int albumID) ;

    // Retrieves all albums by a specific artistID
    List<Album> getAlbumsByArtistId(int artistID) ;

}
