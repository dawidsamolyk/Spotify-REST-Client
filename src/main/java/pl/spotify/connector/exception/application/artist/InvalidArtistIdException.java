package pl.spotify.connector.exception.application.artist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	 * Default constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 * @param cause
	 *            Cause of error.
	 */
	public InvalidArtistIdException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 */
	public InvalidArtistIdException(final String message) {
		super(message);
	}

}
