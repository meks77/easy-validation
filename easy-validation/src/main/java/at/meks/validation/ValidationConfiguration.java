package at.meks.validation;

import java.util.Locale;
import java.util.Optional;

@SuppressWarnings("WeakerAccess")
public class ValidationConfiguration {

    private ValidationConfiguration() {}

    private static Locale locale;

    public static void setLocale(Locale locale) {
        ValidationConfiguration.locale = locale;
    }

    public static Locale getLocale() {
        return Optional.ofNullable(locale).orElse(Locale.getDefault());
    }

}
