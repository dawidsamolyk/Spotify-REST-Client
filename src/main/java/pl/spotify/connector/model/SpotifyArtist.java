package pl.spotify.connector.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents Spotify artist.
 * 
 * @author Dawid Samolyk
 *
 */
public class SpotifyArtist implements Serializable {

	private static final long serialVersionUID = 7237614879055480793L;

	private String name;

	private Collection<String> genres;

	private String photoUrl;

	private Collection<String> similarArtists;

	private Collection<SpotifyTrack> topTracks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<String> getGenres() {
		return genres;
	}

	public void setGenres(Collection<String> genres) {
		this.genres = genres;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Collection<SpotifyTrack> getTopTracks() {
		return topTracks;
	}

	public void setTopTracks(Collection<SpotifyTrack> topTracks) {
		this.topTracks = topTracks;
	}

	public Collection<String> getSimilarArtists() {
		return similarArtists;
	}

	public void setSimilarArtists(Collection<String> similarArtists) {
		this.similarArtists = similarArtists;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, genres, photoUrl, topTracks, similarArtists);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;

		final SpotifyArtist other = (SpotifyArtist) object;

		final boolean areBasicValuesEquals = Objects.equals(name, other.name) && Objects.equals(genres, other.genres)
				&& Objects.equals(photoUrl, other.photoUrl);

		if (!areBasicValuesEquals) {
			return false;
		}
		return Objects.equals(topTracks, other.topTracks) && Objects.equals(similarArtists, other.similarArtists);
	}

	@Override
	public String toString() {
		return "SpotifyArtist [name=" + name + ", genres=" + genres + "]";
	}

}
