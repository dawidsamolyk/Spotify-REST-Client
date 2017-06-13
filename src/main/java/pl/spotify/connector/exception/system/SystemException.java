package pl.spotify.connector.exception.system;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents a system exception (occurred due to back-end error).
 * 
 * @author Dawid Samolyk
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends Exception {

	private static final long serialVersionUID = 5691163591378475146L;

	/**
	 * Default constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 * @param cause
	 *            Cause of error.
	 */
	public SystemException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 */
	public SystemException(final String message) {
		super(message);
	}

}
