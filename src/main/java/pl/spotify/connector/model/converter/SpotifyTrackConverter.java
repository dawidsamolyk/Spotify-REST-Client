package pl.spotify.connector.model.converter;

import java.util.Optional;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.models.Track;

import pl.spotify.connector.model.SpotifyTrack;

/**
 * Provides methods to convert data to {@link SpotifyTrack} instance.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyTrackConverter {

	public static final Track EMPTY_TRACK = new Track();

	/**
	 * Converts Web API's track to {@link SpotifyTrack} instance.
	 * 
	 * @param track
	 *            Track to convert.
	 * @return Converted data.
	 */
	public SpotifyTrack convertFrom(final Track track) {
		final Track toConvert = Optional.ofNullable(track).orElse(EMPTY_TRACK);

		final SpotifyTrack result = new SpotifyTrack();

		result.setTrackName(toConvert.getName());
		result.setDuration(getFormattedDuration(toConvert));
		result.setPreviewUrl(toConvert.getPreviewUrl());

		return result;
	}

	private String getFormattedDuration(Track toConvert) {
		return DurationFormatUtils.formatDurationHMS(toConvert.getDuration());
	}

}
