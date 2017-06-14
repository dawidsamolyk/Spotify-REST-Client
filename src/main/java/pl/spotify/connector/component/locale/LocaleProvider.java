package pl.spotify.connector.component.locale;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Provides current locale.
 * 
 * @author Dawid Samolyk
 *
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LocaleProvider {

	private List<Locale> availableLocalesCache;

	/**
	 * Provides current localization country name.
	 * 
	 * @return Country name.
	 */
	public String getCountryByLocale() {
		final Locale currentLocale = getCurrentLocale();
		final String country = currentLocale.getCountry();

		if (StringUtils.isEmpty(country)) {
			return getAvailableLocales().stream().filter(getLanguageFilter(currentLocale)).findFirst()
					.orElse(currentLocale).getCountry();
		}
		return country;
	}

	private Predicate<? super Locale> getLanguageFilter(final Locale currentLocale) {
		return each -> StringUtils.equalsIgnoreCase(currentLocale.getLanguage(), each.getLanguage())
				&& StringUtils.isNotEmpty(each.getCountry());
	}

	private List<Locale> getAvailableLocales() {
		if (availableLocalesCache == null) {
			availableLocalesCache = Arrays.asList(Locale.getAvailableLocales());
		}
		return availableLocalesCache;
	}

	private Locale getCurrentLocale() {
		return LocaleContextHolder.getLocale();
	}

}
