package pl.spotify.connector.service;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.spotify.connector.SpotifyConnectorApplication;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.artist.Artist;
import pl.spotify.connector.service.artist.SpotifyArtistService;

/**
 * Integration test of the {@link SpotifyArtistService}.
 * 
 * @author Dawid Samolyk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpotifyConnectorApplication.class)
@WebAppConfiguration
public class SpotifyArtistServiceIntegrationTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Autowired
	private SpotifyArtistService objectUnderTest;

	@Test
	public void for_null_artist_should_throw_exception() throws ApplicationException, SystemException {
		exception.expect(InvalidArtistIdException.class);
		objectUnderTest.fetchArtistById(null);
	}
	
	@Test
	public void for_not_existent_artist_id_should_throw_exception() throws ApplicationException, SystemException {
		// given
		String artistId = "unknown";

		// then
		exception.expect(ArtistNotFoundException.class);
		objectUnderTest.fetchArtistById(artistId);
	}

	@Test
	public void for_example_artist_id_should_provide_valid_name() throws ApplicationException, SystemException {
		// given
		String artistId = "0OdUWJ0sBjDrqHygGUXeCF";

		// when
		Artist result = objectUnderTest.fetchArtistById(artistId);

		// then
		assertEquals("Band of Horses", result.getName());
	}

}
