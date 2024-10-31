package persistence;

import musiclibrary.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SongDaoImpl extends sqlDao implements SongDao {

    public SongDaoImpl(String databaseName) {
        super(databaseName);
    }

    @Override
    public void addSong(Song song) throws Exception {
        String sql = "INSERT INTO songs (title, albumID, duration) VALUES (?, ?, ?)";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getAlbumID());
            ps.setInt(3, song.getDuration());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while adding a song.");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteSong(int songID) throws Exception {
        String sql = "DELETE FROM songs WHERE songID = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while deleting a song.");
            ex.printStackTrace();
        }
    }
}
