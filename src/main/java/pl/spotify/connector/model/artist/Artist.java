package pl.spotify.connector.model.artist;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents Spotify artist.
 * 
 * @author Dawid Samolyk
 *
 */
public class Artist implements Serializable {

	private static final long serialVersionUID = 7237614879055480793L;

	private String name;

	public Artist() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final Artist other = (Artist) object;

		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + "]";
	}

}
