package pl.spotify.connector.component.api.web.authenticator;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

import pl.spotify.connector.exception.system.SystemException;

/**
 * Authenticates Spotify Web API.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
public class SpotifyWebApiAuthenticator {

	@Value("${spotifyWebApiClientId}")
	private String clientId;

	@Value("${spotifyWebApiSecretKey}")
	private String secretKey;

	private Api api;

	public Api authenticate() throws SystemException {
		if (this.api == null) {
			final ClientCredentials credentials = getClientCredentials();

			this.api = Api.builder().accessToken(credentials.getAccessToken()).build();
		}
		return this.api;
	}

	private ClientCredentials getClientCredentials() throws SystemException {
		final Api api = Api.builder().clientId(clientId).clientSecret(secretKey).build();

		final ClientCredentialsGrantRequest credentialsRequest = api.clientCredentialsGrant().build();

		try {
			return credentialsRequest.get();

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

}
