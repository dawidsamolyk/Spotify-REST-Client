package pl.spotify.connector.api.web.facade;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.spotify.connector.api.web.SpotifyArtistWebApi;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.system.SystemException;
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
			throws ApplicationException, SystemException {
		return artistApi.getArtistsByName(name, topTracksLimit);
	}

}
