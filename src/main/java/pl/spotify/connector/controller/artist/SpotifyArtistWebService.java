package pl.spotify.connector.controller.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.artist.Artist;
import pl.spotify.connector.service.artist.SpotifyArtistService;

/**
 * Web service for fetching artists' data from the Spotify.
 * 
 * @author Dawid Samolyk
 *
 */
@RestController
@RequestMapping("/artist")
public class SpotifyArtistWebService {

	@Autowired
	private SpotifyArtistService artistService;

	/**
	 * Provides an {@link Artist} which matches input value.
	 * 
	 * @param artist
	 *            Artist ID to.
	 * @return {@link ResponseEntity} which contains found {@link Artist}.
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	@RequestMapping(value = "/{artist}", method = RequestMethod.GET)
	public ResponseEntity<Artist> getArtistById(@PathVariable(name = "artist", required = true) final String artist)
			throws ApplicationException, SystemException {
		final Artist result = artistService.fetchArtistById(artist);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
