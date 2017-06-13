package pl.spotify.connector.exception.application.artist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	 * Default constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 * @param cause
	 *            Cause of error.
	 */
	public ArtistNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 */
	public ArtistNotFoundException(final String message) {
		super(message);
	}

}
