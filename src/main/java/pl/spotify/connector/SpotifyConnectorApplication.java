package pl.spotify.connector;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Spring Boot configured web application.
 * 
 * @author Dawid Samolyk
 *
 */
@SpringBootApplication
public class SpotifyConnectorApplication {

	@Value("${default.locale}")
	private String defaultLocale;

	@Value("${i18n.encoding}")
	private String i18nEncoding;

	@Value("${i18n.path}")
	private String i18nBasePath;

	/**
	 * Main method which starts Spring Boot web application.
	 * 
	 * @param arguments
	 *            Application arguments.
	 */
	public static void main(final String[] arguments) {
		SpringApplication.run(SpotifyConnectorApplication.class, arguments);
	}

	/**
	 * Provides locale resolver.
	 * 
	 * @return Instance of {@link LocaleResolver}.
	 */
	@Bean
	public LocaleResolver createLocaleResolver() {
		final SessionLocaleResolver result = new SessionLocaleResolver();
		result.setDefaultLocale(Locale.forLanguageTag(defaultLocale));

		return result;
	}

	/**
	 * Provides internationalized messages.
	 * 
	 * @return Instance of {@link MessageSource}.
	 */
	@Bean
	public MessageSource createMessageSource() {
		final ResourceBundleMessageSource result = new ResourceBundleMessageSource();
		result.setBasename(i18nBasePath);
		result.setDefaultEncoding(i18nEncoding);

		return result;
	}

}
