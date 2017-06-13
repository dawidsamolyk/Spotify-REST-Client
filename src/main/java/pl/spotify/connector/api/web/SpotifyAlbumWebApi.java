package pl.spotify.connector.api.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.models.Album;

import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.SpotifyAlbum;
import pl.spotify.connector.model.converter.SpotifyAlbumConverter;

/**
 * A component for fetching album from Spotify.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyAlbumWebApi extends AbstractSpotifyWebApi {

	@Autowired
	private SpotifyAlbumConverter albumConverter;

	/**
	 * Provides album by ID.
	 * 
	 * @param albuIds
	 *            Album ID.
	 * @return An album.
	 * @throws ApplicationException
	 *             Thrown when input data is invalid or artist not found.
	 * @throws SystemException
	 *             Thrown when any internal error occurs.
	 */
	public SpotifyAlbum getAlbumById(String id) throws ApplicationException, SystemException {
		final AlbumRequest request = getApi().getAlbum(id).build();

		try {
			return fetchAlbum(request);

		} catch (EmptyResponseException | BadRequestException e) {
			// TODO
			throw new ArtistNotFoundException("spotify.api.artist.notfound.error", e);

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

	private SpotifyAlbum fetchAlbum(AlbumRequest request) throws IOException, WebApiException, ApplicationException {
		final Album result = request.get();

		return albumConverter.convertFrom(result);
	}

}
