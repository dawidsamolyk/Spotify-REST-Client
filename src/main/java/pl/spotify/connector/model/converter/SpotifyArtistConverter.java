package pl.spotify.connector.model.converter;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Image;

import pl.spotify.connector.model.SpotifyArtist;

/**
 * Provides methods to convert data to {@link SpotifyArtist} instance.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyArtistConverter {

	public static final Artist EMPTY_ARTIST = new Artist();

	/**
	 * Converts Web API's artist to {@link SpotifyArtist} instance.
	 * 
	 * @param artist
	 *            Artist to convert.
	 * @return Converted data.
	 */
	public SpotifyArtist convertFrom(final Artist artist) {
		final Artist toConvert = Optional.ofNullable(artist).orElse(EMPTY_ARTIST);

		final SpotifyArtist result = new SpotifyArtist();

		result.setName(toConvert.getName());
		result.setGenres(toConvert.getGenres());
		result.setPhotoUrl(selectFirstUrl(toConvert.getImages()));

		return result;
	}

	private String selectFirstUrl(List<Image> images) {
		return images.stream().map(getImageUrlMapper()).findFirst().orElse(null);
	}

	private Function<? super Image, ? extends String> getImageUrlMapper() {
		return each -> each.getUrl();
	}

}
