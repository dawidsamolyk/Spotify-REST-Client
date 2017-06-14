package pl.spotify.connector.exception.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.exception.system.SpotifyApiException;

/**
 * Handles exceptions.
 * 
 * @author Dawid Samolyk
 *
 */
@Controller
public class ExceptionsHandlingController {

	@Autowired
	private MessagesProvider messagesProvider;

	private String getI18nMessage(ArtistNotFoundException exception) {
		return messagesProvider.get(exception.getMessage());
	}

	@ExceptionHandler(ArtistNotFoundException.class)
	public ArtistNotFoundException exception(ArtistNotFoundException exception) {
		return new ArtistNotFoundException(getI18nMessage(exception));
	}

	@ExceptionHandler(InvalidArtistIdException.class)
	public InvalidArtistIdException exception(InvalidArtistIdException exception) {
		return new InvalidArtistIdException(messagesProvider.get(exception.getMessage()));
	}

	@ExceptionHandler(SpotifyApiException.class)
	public SpotifyApiException exception(SpotifyApiException exception) {
		return new SpotifyApiException(messagesProvider.get(exception.getMessage()));
	}

}
