package persistence;

import musiclibrary.Playlist;
import musiclibrary.Song;

import java.sql.SQLException;
import java.util.List;

public interface PlaylistDao {
   boolean createPlaylist(Playlist playlist);
   boolean addingSongToPlaylist(int playlistID, int songID);
   boolean removeSongFromPlaylist(int playlistID,int songID);
   boolean renamePlaylist(int playlistID, String newName);
   List<Playlist> getUserPaylist(int userID);



}
