package pl.spotify.connector.api;

import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.artist.Artist;

/**
 * An interface which provides access to Spotify data.
 * 
 * @author Dawid Samolyk
 *
 */
public interface SpotifyAccess {

	/**
	 * Provides an artist by given ID.
	 * 
	 * @param id
	 *            ID of an artist.
	 * @return An artist.
	 * @throws ApplicationException
	 *             Thrown when input data is invalid or artist not found.
	 * @throws SystemException
	 *             Thrown when any internal error occurs.
	 */
	public Artist getArtistById(final String id) throws ApplicationException, SystemException;

}
