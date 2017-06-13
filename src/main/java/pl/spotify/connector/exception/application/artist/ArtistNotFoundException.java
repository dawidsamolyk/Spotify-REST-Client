package pl.spotify.connector.exception.application.artist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;

/**
 * Represents error about missing artist (not found).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends ApplicationException {

	private static final long serialVersionUID = 6019925480553425020L;

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String, Throwable)}
	 */
	public ArtistNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String)}
	 */
	public ArtistNotFoundException(final String message) {
		super(message);
	}

}
