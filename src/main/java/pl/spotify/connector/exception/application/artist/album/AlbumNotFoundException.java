package pl.spotify.connector.exception.application.artist.album;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;

/**
 * Represents error about missing album (not found).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 6019925480553425020L;

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String)}
	 */
	public AlbumNotFoundException(final String message) {
		super(message);
	}

}
