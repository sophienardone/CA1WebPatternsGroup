package persistence;

import musiclibrary.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ArtistDao {

    List<Artist> getAllArtists() throws SQLException;

    Artist getArtistById(int artistId) throws SQLException;
}
