package pl.spotify.connector.exception.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents an application exception (occurred due to user's mistake).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 5691163591378475146L;

	/**
	 * Default constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 * @param cause
	 *            Cause of error.
	 */
	public ApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 */
	public ApplicationException(final String message) {
		super(message);
	}

}
