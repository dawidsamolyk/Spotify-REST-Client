package pl.spotify.connector.service.artist;

import static com.google.common.base.Strings.emptyToNull;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.spotify.connector.api.web.facade.SpotifyWebApiFacade;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.SpotifyArtist;

/**
 * Service for fetching Spotify artist data.
 * 
 * @author Dawid Samolyk
 *
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyArtistService {

	@Autowired
	private SpotifyWebApiFacade spotifyApi;

	/**
	 * Provides artists.
	 * 
	 * @param artist
	 *            Artist name.
	 * @param topTracksLimit
	 *            Limit of top tracks to get.
	 * @return Artists.
	 * @throws ApplicationException
	 *             Thrown when input data given by user is invalid.
	 * @throws SystemException
	 *             Thrown when any unexpected system error occurs.
	 */
	public Collection<SpotifyArtist> fetchArtistsByName(String artist, int topTracksLimit)
			throws ApplicationException, SystemException {
		final String artistName = Optional.ofNullable(emptyToNull(artist))
				.orElseThrow(getInvalidArtistIdErrorProvider());

		return spotifyApi.getArtistsByName(artistName, topTracksLimit);
	}

	private Supplier<? extends ApplicationException> getInvalidArtistIdErrorProvider() {
		return () -> new InvalidArtistIdException("invalid.artist.id");
	}

}
