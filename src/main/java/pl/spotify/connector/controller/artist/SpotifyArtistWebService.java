package pl.spotify.connector.controller.artist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.model.SpotifyArtist;
import pl.spotify.connector.service.artist.SpotifyArtistService;

/**
 * Web service for fetching artists' data from the Spotify.
 * 
 * @author Dawid Samolyk
 *
 */
@RestController
@RequestMapping("/artists")
public class SpotifyArtistWebService {

	private static final String DEFAULT_TOP_TRACKS_LIMIT = "5";

	@Autowired
	private SpotifyArtistService artistService;

	/**
	 * Provides an {@link SpotifyArtist} which matches input name.
	 * 
	 * @param artist
	 *            Artist name.
	 * @return {@link ResponseEntity} which contains <code>not null</code>
	 *         collection of {@link SpotifyArtist}.
	 * @throws SpotifyConnectorException
	 *             Thrown when input data given by user is invalid or any
	 *             unexpected system error occurs.
	 */
	@RequestMapping(value = "/{artist}", method = RequestMethod.GET)
	public ResponseEntity<Collection<SpotifyArtist>> getArtists(
			@PathVariable(name = "artist", required = true) final String artist,
			@RequestParam(name = "topTracksLimit", defaultValue = DEFAULT_TOP_TRACKS_LIMIT, required = false) int topTracksLimit)
			throws SpotifyConnectorException {

		final Collection<SpotifyArtist> result = artistService.fetchArtistsByName(artist, topTracksLimit);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
