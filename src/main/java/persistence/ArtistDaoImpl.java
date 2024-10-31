package persistence;

import musiclibrary.Artist;
import musiclibrary.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoImpl implements ArtistDao {

    private final sqlDao sqlDao;


    /**
     * Constructs an ArtistDaoImpl with the specified sqlDao.
     *
     * @param sqlDao the sqlDao used to obtain database connections
     */
    public ArtistDaoImpl(sqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }


    /**
     * Retrieves all artists from the database.
     *
     * @return a List of Artist objects representing all artists in the database.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Artist> getAllArtists() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        String sqlquery = "SELECT * FROM Artists";

        try (Connection connection = sqlDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
             ResultSet resultset = preparedStatement.executeQuery()) {

            while (resultset.next()) {
                Artist artist = new Artist(
                        resultset.getInt("artistID"),
                        resultset.getString("name"),
                        resultset.getString("genre"),
                        resultset.getString("country"),
                        resultset.getTimestamp("debutDate").toLocalDateTime()
                );
                artists.add(artist);
            }
        }
        return artists;
    }


    /**
     * Retrieves an artist by their ID from the database.
     *
     * @param artistId the ID of the artist to retrieve.
     * @return the Artist object representing the artist with the specified ID, or null if not found.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Artist getArtistById(int artistId) throws SQLException {
        Artist artist = null;
        String sqlquery = "SELECT * FROM artists WHERE artistid = ?";

        try (Connection connection = sqlDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlquery)) {

            preparedStatement.setInt(1, artistId);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    artist = new Artist(
                            resultset.getInt("artistID"),
                            resultset.getString("name"),
                            resultset.getString("genre"),
                            resultset.getString("country"),
                            resultset.getTimestamp("debutDate").toLocalDateTime()
                    );
                }
            }
        }
        return artist;
    }


    /**
     * Adds a new artist to the database.
     *
     * @param artist the Artist object containing the artist information to be added.
     * @return the ID of the newly created artist.
     * @throws SQLException if a database access error occurs.
     */
    public int addArtist(Artist artist) throws SQLException {
        String sql = "INSERT INTO Artists (name, genre, country, debutDate) VALUES (?, ?, ?, ?)";
        try (Connection connection = sqlDao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getGenre());
            preparedStatement.setString(3, artist.getCountry());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(artist.getDate()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting artist failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting artist failed, no ID obtained.");
                }
            }
        }
    }


}
