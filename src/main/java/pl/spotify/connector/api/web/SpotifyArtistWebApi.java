package pl.spotify.connector.api.web;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.EmptyResponseException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.ArtistSearchRequest;
import com.wrapper.spotify.models.Artist;

import pl.spotify.connector.exception.SpotifyConnectorException;
import pl.spotify.connector.exception.application.ApplicationException;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.system.SystemException;
import pl.spotify.connector.model.SpotifyArtist;
import pl.spotify.connector.model.SpotifyTrack;
import pl.spotify.connector.model.converter.SpotifyArtistConverter;

/**
 * A component for fetching artists from Spotify.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyArtistWebApi extends AbstractSpotifyWebApi {

	@Autowired
	private SpotifyArtistConverter artistConverter;

	@Autowired
	private SpotifyTrackWebApi tracksApi;

	/**
	 * Provides artists with given names.
	 * 
	 * @param name
	 *            Artist name.
	 * @param topTracksLimit
	 *            Limit of top tracks to get.
	 * @return A collection of artists.
	 * @throws SpotifyConnectorException
	 *             Thrown when input data is invalid or artist not found or any
	 *             internal error occurs.
	 */
	public Collection<SpotifyArtist> getArtistsByName(String name, int topTracksLimit)
			throws SpotifyConnectorException {
		final ArtistSearchRequest request = getApi().searchArtists(name).build();

		try {
			return fetchArtists(request, topTracksLimit);

		} catch (EmptyResponseException | BadRequestException e) {
			getLogger().error(e.getLocalizedMessage(), e);
			throw new ArtistNotFoundException("spotify.api.artist.notfound.error");

		} catch (IOException | WebApiException e) {
			throw new SystemException(e.getLocalizedMessage(), e);
		}
	}

	private Collection<SpotifyArtist> fetchArtists(ArtistSearchRequest request, int topTracksLimit)
			throws IOException, WebApiException, ApplicationException {
		final List<Artist> result = request.get().getItems();

		if (result.isEmpty()) {
			throw new ArtistNotFoundException("spotify.api.artist.notfound.error");
		}
		return result.stream().map(getArtistMapper(topTracksLimit)).collect(Collectors.toList());
	}

	private Function<? super Artist, ? extends SpotifyArtist> getArtistMapper(int topTracksLimit) {
		return eachArtist -> {
			final SpotifyArtist result = artistConverter.convertFrom(eachArtist);
			result.setTopTracks(fetchTopTracks(eachArtist, topTracksLimit));
			result.setSimilarArtists(fetchRelatedArtists(eachArtist));

			return result;
		};
	}

	private Collection<SpotifyTrack> fetchTopTracks(Artist eachArtist, int topTracksLimit) {
		try {
			return tracksApi.getTopTracksByArtistId(eachArtist, topTracksLimit);

		} catch (SpotifyConnectorException e) {
			getLogger().error(e.getLocalizedMessage(), e);

			return Collections.emptyList();
		}
	}

	private Collection<String> fetchRelatedArtists(Artist artist) {
		try {
			final List<Artist> result = getApi().getArtistRelatedArtists(artist.getId()).build().get();
			return result.stream().map(Artist::getName).collect(Collectors.toList());

		} catch (IOException | WebApiException | SystemException e) {
			getLogger().error(e.getLocalizedMessage(), e);

			return Collections.emptyList();
		}
	}

}
