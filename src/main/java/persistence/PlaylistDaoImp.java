package persistence;

import musiclibrary.Playlist;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDaoImp extends sqlDao implements PlaylistDao {


    @Override
    public boolean createPlaylist(Playlist playlist) {
        int rowsEffected = 0;
        Connection conn = super.getConnection();

        String sql = "INSERT into playlists (userID, name, description, creationDate, isPublic) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlist.getUserID());
            ps.setString(2, playlist.getName());
            ps.setString(3, playlist.getDescription());
            ps.setDate(4, (Date) playlist.getCreationDate());
            ps.setBoolean(5, playlist.isPublic());
          rowsEffected = ps.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println(LocalDateTime.now() +": Problem occurred while creating playlist");
            System.out.println("\tError: " + e.getMessage());
        } finally {
            super.freeConnection(conn);
        }
        if (rowsEffected > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean addingSongToPlaylist(int playlistID, int songID) {
        int rowsEffected = 0;
        Connection conn = super.getConnection();
        String sql = "INSERT INTO Playlist_Song (PlaylistID,SongID) VALUES (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,playlistID);
            ps.setInt(2, songID);
          rowsEffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": Problem occurred while adding song to playlist");
            System.out.println("\tError: " + e.getMessage());
        }finally {
            super.freeConnection(conn);
        }

        if (rowsEffected > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistID, int songID) {
        int rowsEffected = 0;
            Connection conn = super.getConnection();
            String sql = "DELETE FROM playlist_song WHERE playlist_id = ? AND song_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, playlistID);
                ps.setInt(2, songID);
               rowsEffected = ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": Problem occurred while trying to remove song from playlist");
                System.out.println("\tError: " + e.getMessage());
            } finally {
                super.freeConnection(conn);
            }

            if (rowsEffected > 0){
                return true;
            }
            else {
                return false;
            }

    }

    @Override
    public boolean renamePlaylist(int playlistID, String newName) {
       int rowsEffected = 0;
        Connection conn = super.getConnection();
        String sql = "UPDATE Playlists SET name = ? WHERE playlistID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, newName);
            ps.setInt(2, playlistID);
           rowsEffected =  ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(LocalDateTime.now() + ": Problem with renaming the playlist");
            System.out.println("\tError: " + e.getMessage());
        } finally {
            super.freeConnection(conn);
        }
        if (rowsEffected > 0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Playlist> getUserPaylist(int userID) {
        List<Playlist> userPlaylist = new ArrayList<>();

        Connection conn = super.getConnection();
        String sql = " SELECT * FROM playlists WHERE userID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, userID);

           try(ResultSet rs = ps.executeQuery()) {
               while (rs.next()){
                   Playlist playlist = new Playlist();
                   playlist.setPlaylistID(rs.getInt("playlistID"));
                   playlist.setUserID(rs.getInt("userID"));
                   playlist.setName(rs.getString("name"));
                   playlist.setDescription(rs.getString("description"));
                   playlist.setCreationDate(rs.getDate("creationDate"));
                   playlist.setPublic(rs.getBoolean("isPublic"));

                   userPlaylist.add(playlist);
               }
           } catch (SQLException e){
               System.out.println(LocalDateTime.now() +  ": SQl Exception occurred when executing sql or processing results.");
               System.out.println("\tError " + e.getMessage());

           }


        }catch (SQLException e){
            System.out.println(LocalDateTime.now() + ": Problem occurred while while getting lists of the playlist ");
            System.out.println("\tError: " + e.getMessage());
        } finally {
            super.freeConnection(conn);
        }

        return userPlaylist;
    }
}
