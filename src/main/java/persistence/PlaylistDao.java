package persistence;

import musiclibrary.Playlist;
import musiclibrary.Song;

import java.sql.SQLException;
import java.util.List;

public interface PlaylistDao {
   void createPlaylist(Playlist playlist);
   void addingSongToPlaylist(int playlistID, int songID);
   void removeSongFromPlaylist(int playlistID,int songID);
   void renamePlaylist(int playlistID, String newName);
   List<Playlist> getUserPaylist(int userID);



}
