import musiclibrary.Artist;
import org.junit.Test;
import persistence.ArtistDao;
import persistence.ArtistDaoImpl;
import persistence.sqlDao;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

public class ArtistDaoImplTest {

    private ArtistDaoImpl artistDaoImpl;
    private sqlDao sqlDao;


    public ArtistDaoImplTest() {
     //   sqlDao = new sqlDao("musiclibrary_test");
       // artistDaoImpl = new ArtistDaoImpl(sqlDao);
    }


    @Test
    public void testAddArtist() {

        ArtistDao artistDao = new ArtistDaoImpl("musiclibrary_test");
        try {
            // Create a new Artist instance
            Artist artist = new Artist("Bryson Tiller", "Rap", "USA", LocalDateTime.now());
            int artistId = artistDao.addArtist(artist);

            assertTrue(artistId > 0, "The artist ID should be a positive integer.");

            List<Artist> artists = artistDao.getAllArtists();
            boolean artistExists = false;
            for (Artist a : artists) {
                if (a.getArtistId() == artistId) {
                    artistExists = true;
                    break;
                }
            }

            assertTrue(artistExists, "The artist should be added successfully.");
        } catch (SQLException e) {
            fail("Exception occurred during addArtist test: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllArtists() {
        ArtistDao artistDao = new ArtistDaoImpl("musiclibrary_test");

        try {
            Artist artist = new Artist("Masego", "jazz", "USA", LocalDateTime.now());
            artistDao.addArtist(artist);

            List<Artist> artists = artistDao.getAllArtists();

            assertFalse(artists.isEmpty(), "The list of artists should not be empty.");

            boolean artistFound = false;
            for (Artist a : artists) {
                if (a.getName().equals("Masego")) {
                    artistFound = true;
                    break;
                }
            }
            assertTrue(artistFound, "The list of artists should include the added artist.");
        } catch (SQLException e) {
            fail("Exception occurred during getAllArtists test: " + e.getMessage());
        }
    }


    @Test
    public void testGetArtistById() {
        ArtistDao artistDao = new ArtistDaoImpl("musiclibrary_test");

        try {
            Artist artist = new Artist("Tate McRae", "Pop", "USA", LocalDateTime.now());
            int artistId = artistDao.addArtist(artist);

            Artist fetchedArtist = artistDao.getArtistById(artistId);

            assertNotNull(fetchedArtist, "The artist should not be null.");

            System.out.println("Fetched Artist ID: " + fetchedArtist.getArtistId());

        } catch (SQLException e) {
            fail("Exception occurred during getArtistById test: " + e.getMessage());
        }
    }


}
