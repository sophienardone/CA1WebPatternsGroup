package persistence;

import musiclibrary.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PlaylistDaoImp extends sqlDao implements PlaylistDao {


    public void createPlaylist(Playlist playlist){
        Connection conn = super.getConnection();

        String sql = "INSERT into playlists (userID, name, description, creationDate) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, playlist.getUserID());
            ps.setString(2, playlist.getName());
            ps.setString(3, playlist.getDescription());
            ps.setTimestamp(4, Timestamp.valueOf(playlist.getCreationDate()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
