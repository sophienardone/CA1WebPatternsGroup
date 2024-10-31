package persistence;

import musiclibrary.Album;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl extends sqlDao implements AlbumDao {

    public AlbumDaoImpl(String databaseName){
        super(databaseName);
    }

    public AlbumDaoImpl(){
        super();
    }


    /**
     * Inserts a new album into the "albums" table and returns its generated ID.
     *
     * @param album The Album object with album details (title, artist ID, release date, genre, label, duration, and price).
     * @return The ID of the newly inserted album, or -1 if insertion failed.
     */

    @Override
    public int addAlbum(Album album)  {
        String sql = "INSERT INTO albums (title, artistID, releaseDate, genre, label, duration, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getArtistID());
            ps.setDate(3, new java.sql.Date(album.getReleaseDate().getTime()));
            ps.setString(4, album.getGenre());
            ps.setString(5, album.getLabel());
            ps.setInt(6, album.getDuration());
            ps.setDouble(7, album.getPrice());

         int rowsAffected =    ps.executeUpdate();

            if (rowsAffected > 0){
                try (Statement statement = conn.createStatement()) {
                    //get the id of the last inserted row
                    ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()");
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while adding an album.");
            ex.printStackTrace();
        }
        return generatedId;
    }


    /**
     * Retrieves an album from the "albums" table by its ID.
     *
     * @param albumID The ID of the album to retrieve.
     * @return  The Album object if found;  null if no matching album exists.
     */

    @Override
    public Album getAlbumById(int albumID)  {
        String sql = "SELECT * FROM albums WHERE albumID = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, albumID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Album(
                            rs.getInt("albumID"),
                            rs.getString("title"),
                            rs.getInt("artistID"),
                            rs.getDate("releaseDate"),
                            rs.getString("genre"),
                            rs.getString("label"),
                            rs.getInt("duration"),
                            rs.getDouble("price")
                    );
                }
            } catch (SQLException ex) {
                System.out.println(LocalDateTime.now() + ": An SQLException occurred while retrieving album by ID.");
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null; // If no album is found
    }


    /**
     * Retrieves all albums by a specific artist from the "albums" table.
     *
     * @param artistID The ID of the artist whose albums are to be retrieved.
     * @return A list of  Album objects by the specified artist; an empty list if no albums are found.
     */
    @Override
    public List<Album> getAlbumsByArtistId(int artistID) {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums WHERE artistID = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Album album = new Album(
                            rs.getInt("albumID"),
                            rs.getString("title"),
                            rs.getInt("artistID"),
                            rs.getDate("releaseDate"),
                            rs.getString("genre"),
                            rs.getString("label"),
                            rs.getInt("duration"),
                            rs.getDouble("price")
                    );
                    albums.add(album);
                }
            } catch (SQLException ex) {
                System.out.println(LocalDateTime.now() + ": An SQLException occurred while retrieving albums by artist ID.");
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return albums;
    }
}
