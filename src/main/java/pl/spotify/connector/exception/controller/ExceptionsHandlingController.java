package pl.spotify.connector.exception.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.application.artist.ArtistNotFoundException;
import pl.spotify.connector.exception.application.artist.InvalidArtistIdException;
import pl.spotify.connector.exception.system.SpotifyApiException;

/**
 * Maps exceptions.
 * 
 * @author Dawid Samolyk
 *
 */
@Controller
public class ExceptionsHandlingController {

	@Autowired
	private MessagesProvider messagesProvider;

	@ExceptionHandler(ArtistNotFoundException.class)
	public ArtistNotFoundException exception(ArtistNotFoundException exception) {
		return new ArtistNotFoundException(messagesProvider.get(exception.getMessage()), exception.getCause());
	}

	@ExceptionHandler(InvalidArtistIdException.class)
	public InvalidArtistIdException exception(InvalidArtistIdException exception) {
		return new InvalidArtistIdException(messagesProvider.get(exception.getMessage()), exception.getCause());
	}
	
	@ExceptionHandler(SpotifyApiException.class)
	public SpotifyApiException exception(SpotifyApiException exception) {
		return new SpotifyApiException(messagesProvider.get(exception.getMessage()));
	}

}
