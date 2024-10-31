import musiclibrary.Album;
import org.junit.jupiter.api.Test;
import persistence.AlbumDaoImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumDaoImplTest {





    // Helper method to compare albums
    private void assertAlbumEquals(Album expected, Album actual) {
        assertEquals(expected.getAlbumID(), actual.getAlbumID());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getArtistID(), actual.getArtistID());
        assertEquals(expected.getReleaseDate(), actual.getReleaseDate());
        assertEquals(expected.getGenre(), actual.getGenre());
        assertEquals(expected.getLabel(), actual.getLabel());
        assertEquals(expected.getDuration(), actual.getDuration());
        assertEquals(expected.getPrice(), actual.getPrice(), 0.01);
    }
//
    @Test
    void testAddAlbum() {
        AlbumDaoImpl albumDao = new AlbumDaoImpl("musiclibrary_test");
        Album album = new Album(12, "Test Album", 1, Date.valueOf(LocalDate.now()), "Pop", "Test Label", 45, 12.99);

       album.setAlbumID(albumDao.addAlbum(album));
        Album retrievedAlbum = albumDao.getAlbumById(album.getAlbumID());

        assertNotNull(retrievedAlbum);
        assertEquals("Test Album", retrievedAlbum.getTitle());
        assertEquals(1, retrievedAlbum.getArtistID());
    }
//
    @Test
    void testGetAlbumById() {
        AlbumDaoImpl albumDao = new AlbumDaoImpl("musiclibrary_test");
        Album album = new Album(10, "Test Album 2", 2, Date.valueOf(LocalDate.now()), "Rock", "Test Label 2", 50, 15.99);
        albumDao.addAlbum(album);

        Album retrievedAlbum = albumDao.getAlbumById(album.getAlbumID());
        assertNotNull(retrievedAlbum);
        assertAlbumEquals(album, retrievedAlbum);
    }
//
    @Test
    void testGetAlbumsByArtistId() {
        AlbumDaoImpl albumDao = new AlbumDaoImpl("musiclibrary_test");

        List<Album> expected1 = new ArrayList<>();
        expected1.add(new Album(2, "Back in Black", 2, new Date(1980-7-25), "Rock", "Atlantic", 2505, 12.99));

        // Adding albums for a specific artist



        List<Album> albums = albumDao.getAlbumsByArtistId(2);

        for(int i = 0; i < expected1.size(); i++){

            assertAlbumEquals(expected1.get(i), albums.get(i));
//            System.out.println(expected1.get(i));
        }


    }
}
