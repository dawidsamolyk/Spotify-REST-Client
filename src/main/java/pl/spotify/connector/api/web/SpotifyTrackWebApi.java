package pl.spotify.connector.api.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TopTracksRequest;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.Track;

import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
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

	private static final SpotifyAlbum EMPTY_ALBUM = new SpotifyAlbum();

	@Autowired
	private SpotifyTrackConverter trackConverter;

	@Autowired
	private SpotifyAlbumWebApi albumApi;

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
		final TopTracksRequest request = getApi().getTopTracksForArtist(artist.getId(), getCountryByLocale()).build();

		try {
			return fetchTopTracks(request, tracksLimit);

		} catch (EmptyResponseException | BadRequestException e) {
			// TODO
			throw new ArtistNotFoundException("spotify.api.artist.notfound.error", e);

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

	private String getCountryByLocale() {
		return LocaleContextHolder.getLocale().getCountry();
	}

	private Collection<SpotifyTrack> fetchTopTracks(TopTracksRequest request, int tracksLimit)
			throws IOException, WebApiException, ApplicationException {
		final List<Track> result = request.get();

		if (result.isEmpty()) {
			// TODO
			throw new ArtistNotFoundException("spotify.api.artist.notfound.error");
		}
		return result.stream().map(getTracksMapper()).limit(tracksLimit).collect(Collectors.toList());
	}

	private Function<? super Track, ? extends SpotifyTrack> getTracksMapper() {
		return eachTrack -> {
			final SpotifyTrack result = trackConverter.convertFrom(eachTrack);

			final SpotifyAlbum album = getAlbumData(eachTrack);
			result.setAlbumName(album.getName());
			result.setAlbumReleaseDate(album.getReleaseDate());

			return result;
		};
	}

	private SpotifyAlbum getAlbumData(Track track) {
		final SimpleAlbum album = track.getAlbum();

		if (album == null) {
			return EMPTY_ALBUM;
		}
		try {
			return albumApi.getAlbumById(album.getId());

		} catch (SpotifyConnectorException e) {
			// TODO message
			getLogger().error(e.getLocalizedMessage(), e);

			return EMPTY_ALBUM;
		}
	}

}
