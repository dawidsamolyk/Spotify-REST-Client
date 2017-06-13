package pl.spotify.connector.exception;

/**
 * Represents an abstract Spotify Connector exception.
 * 
 * @author Dawid Samolyk
 *
 */
public abstract class SpotifyConnectorException extends Exception {

	private static final long serialVersionUID = -834666024520850676L;

	/**
	 * Default constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 * @param cause
	 *            Cause of error.
	 */
	public SpotifyConnectorException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            Message which describes error.
	 */
	public SpotifyConnectorException(final String message) {
		super(message);
	}

}
