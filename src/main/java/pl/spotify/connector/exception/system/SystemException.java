package pl.spotify.connector.exception.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.spotify.connector.exception.SpotifyConnectorException;

/**
 * Represents a system exception (occurred due to back-end error).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends SpotifyConnectorException {

	private static final long serialVersionUID = 5691163591378475146L;

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String, Throwable)}
	 */
	public SystemException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see {@link SpotifyConnectorException#SpotifyConnectorException(String)}
	 */
	public SystemException(final String message) {
		super(message);
	}

}
