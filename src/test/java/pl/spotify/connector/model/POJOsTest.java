package pl.spotify.connector.model;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;

/**
 * Tests POJOs (Plain Old Java Objects).
 * 
 * @author Dawid Samolyk
 *
 */
public class POJOsTest {

	@Test
	public void pojo_should_have_valid_getters_setters_constructors_and_hashCode_equals_methods() {
		final Class<?>[] classesUnderTest = { SpotifyArtist.class, SpotifyAlbum.class, SpotifyTrack.class };

		assertPojoMethodsForAll(classesUnderTest)
				.testing(Method.GETTER, Method.SETTER)
				.testing(Method.EQUALS)
				.testing(Method.HASH_CODE)
				.testing(Method.CONSTRUCTOR)
				.areWellImplemented();
	}

}
