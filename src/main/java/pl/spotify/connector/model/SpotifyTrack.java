package pl.spotify.connector.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents Spotify track.
 * 
 * @author Dawid Samolyk
 *
 */
public class SpotifyTrack implements Serializable {

	private static final long serialVersionUID = 5029764119185139781L;

	private String trackName;

	private String albumName;

	private String albumReleaseDate;

	private String duration;

	private String previewUrl;

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumReleaseDate() {
		return albumReleaseDate;
	}

	public void setAlbumReleaseDate(String albumReleaseDate) {
		this.albumReleaseDate = albumReleaseDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(trackName, albumName, duration, previewUrl, albumReleaseDate);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final SpotifyTrack other = (SpotifyTrack) object;

		return Objects.equals(trackName, other.trackName) && Objects.equals(albumName, other.albumName)
				&& Objects.equals(duration, other.duration) && Objects.equals(previewUrl, other.previewUrl)
				&& Objects.equals(albumReleaseDate, other.albumReleaseDate);
	}

}
