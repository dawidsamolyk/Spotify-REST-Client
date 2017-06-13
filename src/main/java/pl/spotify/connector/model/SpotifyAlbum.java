package pl.spotify.connector.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents Spotify album.
 * 
 * @author Dawid Samolyk
 *
 */
public class SpotifyAlbum implements Serializable {

	private static final long serialVersionUID = 5029764119185139781L;

	private String name;

	private String releaseDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, releaseDate);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final SpotifyAlbum other = (SpotifyAlbum) object;

		return Objects.equals(name, other.name) && Objects.equals(releaseDate, other.releaseDate);
	}

	@Override
	public String toString() {
		return "SpotifyAlbum [name=" + name + ", releaseDate=" + releaseDate + "]";
	}

}
