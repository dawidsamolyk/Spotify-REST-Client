package pl.spotify.connector.model;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Method;
import pl.spotify.connector.model.artist.Artist;

/**
 * Tests POJOs (Plain Old Java Objects).
 * 
 * @author Dawid Samolyk
 *
 */
public class PojoTest {

	@Test
	public void pojo_should_have_valid_getters_setters_constructors_and_hashCode_equals_toString_methods() {
		final Class<?>[] classesUnderTest = { Artist.class };

		assertPojoMethodsForAll(classesUnderTest)
				.testing(Method.GETTER, Method.SETTER)
				.testing(Method.TO_STRING)
				.testing(Method.EQUALS)
				.testing(Method.HASH_CODE)
				.testing(Method.CONSTRUCTOR)
				.areWellImplemented();
	}

}
