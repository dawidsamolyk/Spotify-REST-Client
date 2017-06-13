package pl.spotify.connector.api;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.ClientCredentials;

/**
 * Tests foreign API to know how to deal with it.
 * 
 * @author Dawid Samolyk
 *
 */
@Ignore("Ignores that test because it was created only to learn about foreign API usage.")
public class SpotifyForeignWebApiTest {

	private static final String CLIENT_ID = "<ENTER_YOUT_DATA>";
	private static final String CLIENT_SECRET = "<ENTER_YOUT_DATA>";

	@Test
	public void should_be_able_to_fetch_album_without_authorization() throws IOException, WebApiException {
		// given
		ClientCredentials credentials = getClientCredentials();
		Api api = Api.builder().accessToken(credentials.getAccessToken()).build();

		// when
		Album result = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").build().get();

		// then
		assertNotNull(result);
	}

	private ClientCredentials getClientCredentials() throws IOException, WebApiException {
		Api api = Api.builder().clientId(CLIENT_ID).clientSecret(CLIENT_SECRET).build();
		ClientCredentialsGrantRequest credentialsRequest = api.clientCredentialsGrant().build();
		ClientCredentials credentials = credentialsRequest.get();
		return credentials;
	}

}
