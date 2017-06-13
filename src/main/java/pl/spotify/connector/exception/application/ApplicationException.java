package pl.spotify.connector.exception.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.spotify.connector.exception.SpotifyConnectorException;

/**
 * Represents an application exception (occurred due to user's mistake).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApplicationException extends SpotifyConnectorException {

	private static final long serialVersionUID = 5691163591378475146L;

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String, Throwable)}
	 */
	public ApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String)}
	 */
	public ApplicationException(final String message) {
		super(message);
	}

}
