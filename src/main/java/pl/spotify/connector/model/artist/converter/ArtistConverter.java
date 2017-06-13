package pl.spotify.connector.model.artist.converter;

import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pl.spotify.connector.model.artist.Artist;

/**
 * Provides methods to convert data to {@link Artist} instance.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ArtistConverter {

	/**
	 * Converts Web API's artist to {@link Artist} instance.
	 * 
	 * @param artist
	 *            Artist to convert.
	 * @return Converted data.
	 */
	public Artist convertFrom(final com.wrapper.spotify.models.Artist artist) {
		final com.wrapper.spotify.models.Artist toConvert = Optional.ofNullable(artist)
				.orElse(new com.wrapper.spotify.models.Artist());

		final Artist result = new Artist();

		result.setName(toConvert.getName());

		return result;
	}

}
