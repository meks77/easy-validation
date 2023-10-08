package at.meks.validation.args.errormessage;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceBundleErrorMessageTest {

    @Test
    void testGermanUmlaute() {
        Locale.setDefault(Locale.GERMAN);
        var message = new ResourceBundleErrorMessage("comparable.isGreater", "argName", 1, 2);
        assertThat(message.asText())
                .isEqualTo(
                        "Argument \"%s\" mit dem Wert \"%s\" muss größer als \"%s\" sein",
                        "argName", 1, 2);
    }


}