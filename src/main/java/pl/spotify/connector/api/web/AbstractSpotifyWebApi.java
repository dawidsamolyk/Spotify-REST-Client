package pl.spotify.connector.api.web;

import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.Api;

import pl.spotify.connector.api.web.authenticator.SpotifyWebApiAuthenticator;
import pl.spotify.connector.component.messages.MessagesProvider;
import pl.spotify.connector.exception.system.SpotifyApiException;
import pl.spotify.connector.exception.system.SystemException;

/**
 * A component for fetching artists from Spotify.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public abstract class AbstractSpotifyWebApi {

	@Autowired
	private SpotifyWebApiAuthenticator spotifyAuthenticator;

	@Autowired
	private MessagesProvider messagesProvider;

	private Logger logger;

	protected Api getApi() throws SystemException {
		return Optional.ofNullable(spotifyAuthenticator.authenticate()).orElseThrow(getInitializationErrorProvider());
	}

	private Supplier<SystemException> getInitializationErrorProvider() {
		return () -> new SpotifyApiException(messagesProvider.get("spotify.api.initialization.error"));
	}

	protected Logger getLogger() {
		if (logger == null) {
			logger = LoggerFactory.getLogger(getClass());
		}
		return logger;
	}

}
