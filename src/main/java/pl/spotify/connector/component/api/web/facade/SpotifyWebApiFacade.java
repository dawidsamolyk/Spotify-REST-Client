package pl.spotify.connector.component.api.web.facade;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.spotify.connector.component.api.web.SpotifyArtistWebApi;
import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.model.SpotifyArtist;

/**
 * A facade for the Spotify Web API. Enables to get Spotify data via REST.
 * 
 * @author Dawid Samolyk
 *
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyWebApiFacade {

	@Autowired
	private SpotifyArtistWebApi artistApi;

	/**
	 * @see {@link SpotifyArtistWebApi#getArtistsByName(String)}
	 */
	public Collection<SpotifyArtist> getArtistsByName(String name, int topTracksLimit)
			throws SpotifyConnectorException {
		return artistApi.getArtistsByName(name, topTracksLimit);
	}

}
