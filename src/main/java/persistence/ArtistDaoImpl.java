package persistence;

import musiclibrary.Artist;
import musiclibrary.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoImpl implements ArtistDao {

    private final sqlDao sqlDao;

    public ArtistDaoImpl(sqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }

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
                        resultset.getTimestamp("date").toLocalDateTime()
                );
                artists.add(artist);
            }
        }
        return artists;
    }


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
                            resultset.getTimestamp("date").toLocalDateTime()
                    );
                }
            }
        }
        return artist;
    }


}



