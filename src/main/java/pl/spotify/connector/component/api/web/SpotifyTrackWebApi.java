package pl.spotify.connector.component.api.web;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TopTracksRequest;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.Track;

import pl.spotify.connector.component.locale.LocaleProvider;
import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.SpotifyAlbum;
import pl.spotify.connector.model.SpotifyTrack;
import pl.spotify.connector.model.converter.SpotifyTrackConverter;

/**
 * A component for fetching artists from Spotify.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyTrackWebApi extends AbstractSpotifyWebApi {

	@Autowired
	private SpotifyTrackConverter trackConverter;

	@Autowired
	private SpotifyAlbumWebApi albumApi;

	@Autowired
	private MessagesProvider messagesProvider;

	@Autowired
	private LocaleProvider localeProvider;

	/**
	 * Provides top tracks (for a currently logged in user's locale) for given
	 * artist.
	 * 
	 * @param artist
	 *            Artist.
	 * @param tracksLimit
	 *            Limit of tracks to get.
	 * @return A collection of top tracks.
	 * @throws SpotifyConnectorException
	 *             Thrown when input data is invalid or artist not found or any
	 *             internal error occurs.
	 */
	public Collection<SpotifyTrack> getTopTracksByArtistId(Artist artist, int tracksLimit)
			throws SpotifyConnectorException {
		final String country = localeProvider.getCountryByLocale();
		final TopTracksRequest request = getApi().getTopTracksForArtist(artist.getId(), country).build();

		try {
			return fetchTopTracks(request, tracksLimit);

		} catch (EmptyResponseException | BadRequestException e) {
			getLogger().error(messagesProvider.get("spotify.api.album.notfound.error", e.getLocalizedMessage()), e);

			return Collections.emptyList();

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

	private Collection<SpotifyTrack> fetchTopTracks(TopTracksRequest request, int tracksLimit)
			throws IOException, WebApiException, ApplicationException {
		final List<Track> result = request.get();

		return result.stream().map(getTracksMapper()).limit(tracksLimit).collect(Collectors.toList());
	}

	private Function<? super Track, ? extends SpotifyTrack> getTracksMapper() {
		return eachTrack -> {
			final SpotifyTrack result = trackConverter.convertFrom(eachTrack);

			final Optional<SpotifyAlbum> album = getAlbumData(eachTrack);
			if (album.isPresent()) {
				result.setAlbumName(album.get().getName());
				result.setAlbumReleaseDate(album.get().getReleaseDate());
			}

			return result;
		};
	}

	private Optional<SpotifyAlbum> getAlbumData(Track track) {
		final SimpleAlbum album = track.getAlbum();

		if (album == null) {
			return Optional.empty();
		}
		try {
			return albumApi.getAlbumById(album.getId());

		} catch (SpotifyConnectorException e) {
			getLogger().error(messagesProvider.get("spotify.api.album.notfound.error", e.getLocalizedMessage()), e);

			return Optional.empty();
		}
	}

}
