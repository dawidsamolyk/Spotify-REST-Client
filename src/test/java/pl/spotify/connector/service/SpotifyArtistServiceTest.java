package pl.spotify.connector.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pl.spotify.connector.api.web.facade.SpotifyWebApiFacade;
import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.service.artist.SpotifyArtistService;

/**
 * Unit test of the {@link SpotifyArtistService}.
 * 
 * @author Dawid Samolyk
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SpotifyArtistServiceTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Mock
	private SpotifyWebApiFacade mockSpotifyApi;

	@Mock
	private MessagesProvider mockMessageProvider;

	@InjectMocks
	private SpotifyArtistService objectUnderTest;

	@Test
	public void if__artist_name_is_null_then_should_throw_exception() throws SpotifyConnectorException {
		exception.expect(InvalidArtistIdException.class);
		objectUnderTest.fetchArtistsByName(null, 0);
	}

	@Test
	public void for_empty_artist_name_should_throw_exception() throws SpotifyConnectorException {
		exception.expect(InvalidArtistIdException.class);
		objectUnderTest.fetchArtistsByName("", 0);
	}

	@Test
	public void when_artist_name_contains_only_blank_chars_then_should_throw_exception()
			throws SpotifyConnectorException {
		exception.expect(InvalidArtistIdException.class);
		objectUnderTest.fetchArtistsByName("  \t 	", 0);
	}

	@Test
	public void if_artist_name_is_not_empty_then_should_call_Spotify_API() throws SpotifyConnectorException {
		// given
		String artist = "Test artist name";
		int topTracksLimit = 0;

		// when
		objectUnderTest.fetchArtistsByName(artist, topTracksLimit);

		// then
		verify(mockSpotifyApi, times(1)).getArtistsByName(artist, topTracksLimit);
	}

}
