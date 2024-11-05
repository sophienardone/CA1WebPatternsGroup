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
        //creates a new album objects
        Album album = new Album(12, "Test Album", 1, Date.valueOf(LocalDate.now()), "Pop", "Test Label", 45, 12.99);
// Calls addAlbum to insert the album into the database and set the generated album ID
       album.setAlbumID(albumDao.addAlbum(album));
       //Gets album from the database by its ID
        Album retrievedAlbum = albumDao.getAlbumById(album.getAlbumID());

        // Check that the retrieved album is not null
        assertNotNull(retrievedAlbum);
        // Checks if the retrieved album’s title matches the expected title
        assertEquals("Test Album", retrievedAlbum.getTitle());
        // Checks if  the retrieved album's artist ID matches the expected artist ID
        assertEquals(1, retrievedAlbum.getArtistID());
    }
//
    @Test
    void testGetAlbumById() {
        // Initializes the AlbumDaoImpl object, points it to the database i want.
        AlbumDaoImpl albumDao = new AlbumDaoImpl("musiclibrary_test");
        //Creates a new Album object with sample data to add to the database
        Album album = new Album(10, "Test Album 2", 2, Date.valueOf(LocalDate.now()), "Rock", "Test Label 2", 50, 15.99);
        //adds album to the database
        albumDao.addAlbum(album);
      // Retrieves the album from the database using its ID
        Album retrievedAlbum = albumDao.getAlbumById(album.getAlbumID());
        // Checking that the retrieved album is not null
        assertNotNull(retrievedAlbum);
        // Compares the original and retrieved albums to ensure they are equal in all fields
        assertAlbumEquals(album, retrievedAlbum);
    }
//
    @Test
    void testGetAlbumsByArtistId() {
        // Initializes the AlbumDaoImpl object, points it to the database i want.
        AlbumDaoImpl albumDao = new AlbumDaoImpl("musiclibrary_test");
        // Create a list of expected albums for an artist with ID 2
        List<Album> expected1 = new ArrayList<>();
        expected1.add(new Album(2, "Back in Black", 2, new Date(1980-7-25), "Rock", "Atlantic", 2505, 12.99));




        // Gets the list of albums associated with artist ID 2 from the database
        List<Album> albums = albumDao.getAlbumsByArtistId(2);

        for(int i = 0; i < expected1.size(); i++){
           //checking that the expected album and the retrieved album are equal
            assertAlbumEquals(expected1.get(i), albums.get(i));
//            System.out.println(expected1.get(i));
        }


    }
}
