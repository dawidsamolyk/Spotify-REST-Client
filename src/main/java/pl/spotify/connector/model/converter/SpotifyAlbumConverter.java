package pl.spotify.connector.model.converter;

import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.models.Album;

import pl.spotify.connector.model.SpotifyAlbum;

/**
 * Provides methods to convert data to {@link SpotifyAlbum} instance.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyAlbumConverter {

	public static final Album EMPTY_ALBUM = new Album();

	/**
	 * Converts Web API's album to {@link SpotifyAlbum} instance.
	 * 
	 * @param album
	 *            Album to convert.
	 * @return Converted data.
	 */
	public SpotifyAlbum convertFrom(final Album album) {
		final Album toConvert = Optional.ofNullable(album).orElse(EMPTY_ALBUM);

		final SpotifyAlbum result = new SpotifyAlbum();

		result.setName(toConvert.getName());
		result.setReleaseDate(toConvert.getReleaseDate());

		return result;
	}

}
