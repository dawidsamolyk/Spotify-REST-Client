package pl.spotify.connector.component.api.web;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.models.Album;

import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;
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

	@Autowired
	private MessagesProvider messagesProvider;

	/**
	 * Provides album by ID.
	 * 
	 * @param albuIds
	 *            Album ID.
	 * @return An album.
	 * @throws SpotifyConnectorException
	 *             Thrown when input data is invalid or artist not found or any
	 *             internal error occurs.
	 */
	public Optional<SpotifyAlbum> getAlbumById(String id) throws SpotifyConnectorException {
		final AlbumRequest request = getApi().getAlbum(id).build();

		try {
			return Optional.of(fetchAlbum(request));

		} catch (EmptyResponseException | BadRequestException e) {
			getLogger().error(messagesProvider.get("spotify.api.album.notfound.error", e.getLocalizedMessage()), e);

			return Optional.empty();

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

	private SpotifyAlbum fetchAlbum(AlbumRequest request) throws IOException, WebApiException, ApplicationException {
		final Album result = request.get();

		return albumConverter.convertFrom(result);
	}

}
