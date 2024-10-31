package persistence;

import musiclibrary.Song;

import java.sql.*;
import java.time.LocalDateTime;

public class SongDaoImpl extends sqlDao implements SongDao {



    public SongDaoImpl(String databaseName){
        super(databaseName);
    }

    public SongDaoImpl(){
        super();
    }



    @Override
    public int addSong(Song song) throws Exception {
        String sql = "INSERT INTO songs (title, albumID, duration) VALUES (?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getAlbumID());
            ps.setInt(3, song.getDuration());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the ID of the last inserted row
                try (Statement statement = conn.createStatement();
                     ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()")) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while adding a song.");
            ex.printStackTrace();
        }

        return generatedId;
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

    public Song getSongById(int songID) {
        Song song = null;
        Connection conn = super.getConnection();

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM songs WHERE songID = ?")) {
            ps.setInt(1, songID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    song = new Song(
                            rs.getInt("songID"),
                            rs.getString("title"),
                            rs.getInt("albumID"),
                            rs.getInt("duration")
                    );
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": An SQLException occurred while running the query or processing the result.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": An SQLException occurred while preparing the SQL statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return song;
    }



}
