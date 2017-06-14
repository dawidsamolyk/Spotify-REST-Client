package pl.spotify.connector.exception.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.exception.system.SpotifyApiException;

/**
 * Unit test of {@link ExceptionsHandlingController}.
 * 
 * @author Dawid Samolyk
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ExceptionsHandlingControllerTest {

	@Mock
	private MessagesProvider messagesProvider;

	@InjectMocks
	private ExceptionsHandlingController objectUnderTest;

	@Test
	public void should_use_exception_message_as_a_code_of_i18n_text_for_ArtistNotFoundException() {
		// given
		String exceptionMessage = "test.code";
		String i18nText = "Test value";

		when(messagesProvider.get(exceptionMessage)).thenReturn(i18nText);

		// when
		ArtistNotFoundException result = objectUnderTest.exception(new ArtistNotFoundException(exceptionMessage));

		// then
		assertEquals(i18nText, result.getMessage());
	}

	@Test
	public void should_use_exception_message_as_a_code_of_i18n_text_for_InvalidArtistIdException() {
		// given
		String exceptionMessage = "test.code";
		String i18nText = "Test value";

		when(messagesProvider.get(exceptionMessage)).thenReturn(i18nText);

		// when
		InvalidArtistIdException result = objectUnderTest.exception(new InvalidArtistIdException(exceptionMessage));

		// then
		assertEquals(i18nText, result.getMessage());
	}

	@Test
	public void should_use_exception_message_as_a_code_of_i18n_text_for_SpotifyApiException() {
		// given
		String exceptionMessage = "test.code";
		String i18nText = "Test value";

		when(messagesProvider.get(exceptionMessage)).thenReturn(i18nText);

		// when
		SpotifyApiException result = objectUnderTest.exception(new SpotifyApiException(exceptionMessage));

		// then
		assertEquals(i18nText, result.getMessage());
	}

}
