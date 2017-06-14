package pl.spotify.connector.controller.artist;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.spotify.connector.SpotifyConnectorApplication;
import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.model.SpotifyArtist;

/**
 * Integration test of the {@link SpotifyArtistWebService}. Example Spotify Web
 * API credentials are used - please do not reuse them.
 * 
 * @author Dawid Samolyk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpotifyConnectorApplication.class, properties = {
		"spotifyWebApiClientId=12627e1d5fb9495d8f609ff229e0cb43",
		"spotifyWebApiSecretKey=9b472a1ed2024c0e9fae870e9c9a12a2" })
@WebAppConfiguration
public class SpotifyArtistWebServiceIntegrationTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Autowired
	private SpotifyArtistWebService objectUnderTest;

	@Test
	public void for_null_artist_name_should_throw_exception() throws SpotifyConnectorException {
		exception.expect(InvalidArtistIdException.class);
		objectUnderTest.getArtists(null, 0);
	}

	@Test
	public void for_not_existent_artist_name_should_throw_exception() throws SpotifyConnectorException {
		// given
		String artistName = "bbakf512uienfnjsa";

		// then
		exception.expect(ArtistNotFoundException.class);
		objectUnderTest.getArtists(artistName, 0);
	}

	@Test
	public void for_example_artist_name_should_provide_valid_data() throws SpotifyConnectorException {
		// given
		String artistName = "Band of Horses";
		int topTracksLimit = 5;

		// when
		SpotifyArtist result = objectUnderTest.getArtists(artistName, topTracksLimit).getBody().stream().findFirst()
				.orElse(new SpotifyArtist());

		// then
		assertEquals("Band of Horses", result.getName());
		assertEquals("https://i.scdn.co/image/0f9a5013134de288af7d49a962417f4200539b47", result.getPhotoUrl());
		assertTrue(result.getSimilarArtists()
				.containsAll(Arrays.asList("The National", "Rogue Wave", "My Morning Jacket", "The Shins")));
		assertTrue(result.getGenres().containsAll(Arrays.asList("chamber pop", "folk-pop", "indie folk", "indie rock",
				"neo mellow", "stomp and holler")));
		assertTrue(result.getTopTracks().size() <= topTracksLimit);
	}

}
