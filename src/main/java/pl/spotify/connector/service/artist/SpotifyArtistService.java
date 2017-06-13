package pl.spotify.connector.service.artist;

import static com.google.common.base.Strings.emptyToNull;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pl.spotify.connector.api.SpotifyAccess;
import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.artist.Artist;

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
	private SpotifyAccess spotifyApi;

	@Autowired
	private MessagesProvider messagesProvider;

	/**
	 * Provides artist data.
	 * 
	 * @param id
	 *            Artist ID.
	 * @return Artist.
	 * @throws ApplicationException
	 *             Thrown when input data given by user is invalid.
	 * @throws SystemException
	 *             Thrown when any unexpected system error occurs.
	 */
	public Artist fetchArtistById(final String id) throws ApplicationException, SystemException {
		final String artistId = Optional.ofNullable(emptyToNull(id)).orElseThrow(getInvalidArtistIdErrorProvider());

		return spotifyApi.getArtistById(artistId);
	}

	private Supplier<? extends ApplicationException> getInvalidArtistIdErrorProvider() {
		return () -> new InvalidArtistIdException(messagesProvider.get("invalid.artist.id"));
	}

}
