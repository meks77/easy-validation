package at.meks.validation;

import org.junit.Test;

import java.util.Locale;

import static org.fest.assertions.api.Assertions.assertThat;

public class ValidationConfigurationTest {

    @Test
    public void givenLocaleItWhenGetLocaleReturnsLocaleIt() {
        ValidationConfiguration.setLocale(Locale.ITALIAN);
        assertThat(ValidationConfiguration.getLocale()).isEqualTo(Locale.ITALIAN);
    }

    @Test
    public void givenNullLocaleWhenGetLocaleReturnsSystemDefault() {
        ValidationConfiguration.setLocale(null);
        assertThat(ValidationConfiguration.getLocale()).isEqualTo(Locale.getDefault());
    }
}