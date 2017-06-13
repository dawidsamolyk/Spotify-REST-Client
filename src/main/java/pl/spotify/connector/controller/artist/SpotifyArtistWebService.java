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

import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.system.SystemException;
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

	@Autowired
	private SpotifyArtistService artistService;

	/**
	 * Provides an {@link SpotifyArtist} which matches input value.
	 * 
	 * @param artist
	 *            Artist name.
	 * @return {@link ResponseEntity} which contains found
	 *         {@link SpotifyArtist}.
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	@RequestMapping(value = "/{artist}", method = RequestMethod.GET)
	public ResponseEntity<Collection<SpotifyArtist>> getArtists(
			@PathVariable(name = "artist", required = true) final String artist,
			@RequestParam(name = "topTracksLimit", defaultValue = "5", required = false) int topTracksLimit)
			throws ApplicationException, SystemException {

		final Collection<SpotifyArtist> result = artistService.fetchArtistsByName(artist, topTracksLimit);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
