package pl.spotify.connector.exception.application.artist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;

/**
 * Represents error about invalid artist ID.
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArtistIdException extends ApplicationException {

	private static final long serialVersionUID = 2045590509285264731L;

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String, Throwable)}
	 */
	public InvalidArtistIdException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String)}
	 */
	public InvalidArtistIdException(final String message) {
		super(message);
	}

}
