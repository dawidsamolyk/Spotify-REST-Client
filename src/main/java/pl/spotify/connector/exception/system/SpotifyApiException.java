package pl.spotify.connector.exception.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	 * Default constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 */
	public SpotifyApiException(String message) {
		super(message);
	}

}
