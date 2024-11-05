import org.junit.jupiter.api.Test;
import persistence.SongDaoImpl;
import musiclibrary.Song;

import static org.junit.jupiter.api.Assertions.*;

public class SongDaoImplTest {

    // Helper method to compare songs
    private void assertSongEquals(Song expected, Song actual) {
        assertEquals(expected.getSongID(), actual.getSongID());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getAlbumID(), actual.getAlbumID());
        assertEquals(expected.getDuration(), actual.getDuration());
    }

    @Test
    void testAddSong() throws Exception {
        SongDaoImpl songDao = new SongDaoImpl("musiclibrary_test");
        Song song = new Song(0, "Test Song", 1, 180);

        // Add song and retrieve generated ID
        int generatedId = songDao.addSong(song);
        assertTrue(generatedId > 0);

        // Retrieve song by ID and verify
        Song retrievedSong = songDao.getSongById(generatedId);
        song = new Song(generatedId, song.getTitle(), song.getAlbumID(), song.getDuration()); // Update song with generated ID
        assertNotNull(retrievedSong);
        assertSongEquals(song, retrievedSong);
    }

    @Test
    void testDeleteSong() throws Exception {
        SongDaoImpl songDao = new SongDaoImpl("musiclibrary_test");

        // Add a song to delete
        Song song = new Song(0, "Delete Test Song", 1, 200);
        int generatedId = songDao.addSong(song);
        assertTrue(generatedId > 0);

        // Delete song
        songDao.deleteSong(generatedId);

        // Try retrieving the deleted song and verify it's null
        Song retrievedSong = songDao.getSongById(generatedId);
        assertNull(retrievedSong);
    }

    @Test
    void getSongById_NoMatchFound() throws Exception {
        SongDaoImpl songDao = new SongDaoImpl("musiclibrary_test");

        // Try to retrieve a song that does not exist
        Song result = songDao.getSongById(9999);
        assertNull(result);
    }

    @Test
    void getSongById_MatchFound() throws Exception {
        SongDaoImpl songDao = new SongDaoImpl("musiclibrary_test");

        // Add a song to be retrieved later
        Song expected = new Song(1, "Beat it", 1, 258);
        int generatedId = songDao.addSong(expected);
        assertTrue(generatedId > 0);

        // Update expected with the generated ID
        expected = new Song(generatedId, expected.getTitle(), expected.getAlbumID(), expected.getDuration());

        // Retrieve and verify the song
        Song result = songDao.getSongById(generatedId);
        assertNotNull(result);
        assertSongEquals(expected, result);
    }
}
