package persistence;

import musiclibrary.Album;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl extends sqlDao implements AlbumDao {


    public AlbumDaoImpl(String databaseName) {
        super(databaseName);
    }

    @Override
    public void addAlbum(Album album) throws Exception {
        String sql = "INSERT INTO albums (title, artistID, releaseDate, genre, label, duration, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getArtistID());
            ps.setDate(3, new java.sql.Date(album.getReleaseDate().getTime()));
            ps.setString(4, album.getGenre());
            ps.setString(5, album.getLabel());
            ps.setInt(6, album.getDuration());
            ps.setDouble(7, album.getPrice());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while adding an album.");
            ex.printStackTrace();
        }
    }

    @Override
    public Album getAlbumById(int albumID) throws Exception {
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
        }
        return null; // If no album is found
    }

    @Override
    public List<Album> getAlbumsByArtistId(int artistID) throws Exception {
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
        }
        return albums;
    }
}
