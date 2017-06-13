package pl.spotify.connector.exception.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.spotify.connector.exception.SpotifyConnectorException;

/**
 * Represents a Spotify exception (occurred due to Web API error).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SpotifyApiException extends SystemException {

	private static final long serialVersionUID = 4537462990263334992L;

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String)}
	 */
	public SpotifyApiException(String message) {
		super(message);
	}

}
