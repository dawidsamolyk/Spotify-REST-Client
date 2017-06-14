package pl.spotify.connector.component.messages;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Provides internationalized messages.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessagesProvider {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Provides internationalized message.
	 * 
	 * @param code
	 *            Code of a message.
	 * @param arguments
	 *            Additional arguments to include into message.
	 * @return An internationalized message.
	 */
	public String get(final String code, final Object... arguments) {
		return messageSource.getMessage(code, arguments, StringUtils.EMPTY, getLocale());
	}

	private Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

}
