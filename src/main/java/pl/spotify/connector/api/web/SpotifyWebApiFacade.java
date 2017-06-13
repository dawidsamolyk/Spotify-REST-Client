package pl.spotify.connector.api.web;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.ArtistRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

import pl.spotify.connector.api.SpotifyAccess;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.artist.Artist;
import pl.spotify.connector.model.artist.converter.ArtistConverter;

/**
 * A facade for the Spotify Web API. Enables to get Spotify data via REST.
 * 
 * @author Dawid Samolyk
 *
 */
@Service("Web API")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyWebApiFacade implements SpotifyAccess {

	private Api api;

	@Value("${spotify.web.api.client.id}")
	private String clientId;

	@Value("${spotify.web.api.client.secret}")
	private String clientSecret;

	@Autowired
	private ArtistConverter artistConverter;

	/**
	 * @see {@link SpotifyAccess#getArtistById(String)}
	 */
	@Override
	public Artist getArtistById(String id) throws ApplicationException, SystemException {
		final Api api = Optional.ofNullable(getApi()).orElseThrow(getInitializationErrorProvider());
		final ArtistRequest request = api.getArtist(id).build();

		try {
			return artistConverter.convertFrom(request.get());

		} catch (EmptyResponseException | BadRequestException e) {
			throw new ArtistNotFoundException("spotify.api.artist.notfound.error", e);

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

	private Supplier<? extends SystemException> getInitializationErrorProvider() {
		return () -> new SystemException("spotify.api.initialization.error");
	}

	private Api getApi() throws SystemException {
		if (this.api == null) {
			final ClientCredentials credentials = getClientCredentials();

			this.api = Api.builder().accessToken(credentials.getAccessToken()).build();
		}
		return this.api;
	}

	private ClientCredentials getClientCredentials() throws SystemException {
		final Api api = Api.builder().clientId(clientId).clientSecret(clientSecret).build();
		final ClientCredentialsGrantRequest credentialsRequest = api.clientCredentialsGrant().build();

		try {
			return credentialsRequest.get();

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

}
