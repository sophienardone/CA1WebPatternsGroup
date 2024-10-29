package persistence;

import musiclibrary.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class PlaylistDaoImp extends sqlDao implements PlaylistDao {


    @Override
    public void createPlaylist(Playlist playlist) {
        Connection conn = super.getConnection();

        String sql = "INSERT into playlists (userID, name, description, creationDate, isPublic) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, playlist.getUserID());
            ps.setString(2, playlist.getName());
            ps.setString(3, playlist.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(playlist.getCreationDate()));
            ps.setBoolean(5, playlist.isPublic());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() +": Problem occurred while creating playlist");
            System.out.println("\tError: " + e.getMessage());
        }
    }

    @Override
    public void addingSongToPlaylist(int playlistID, int songID) {

        Connection conn = super.getConnection();
        String sql = "INSERT INTO Playlist_Song (PlaylistID,SongID) VALUES (?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,playlistID);
            ps.setInt(2, songID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": Problem occurred while adding song to playlist");
            System.out.println("\tError: " + e.getMessage());
        }


    }

    @Override
    public void removeSongFromPlaylist(int playlistID, int songID) {

    }

    @Override
    public void renamePlaylist(int playlistID, String newName) {

    }

    @Override
    public List<Playlist> getUserPaylist(int userID) {
        return List.of();
    }
}
