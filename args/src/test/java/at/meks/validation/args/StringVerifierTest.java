package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class StringVerifierTest extends AbstractVerifierTest<String, StringVerifier> {

    private static final String VALIDATED_VALUE = "validatedValue";
    private final StringVerifier verifier = new StringVerifier("asdf");

    @Override
    protected StringVerifier getVerifierWithValidatedValue() {
        return new StringVerifier(VALIDATED_VALUE);
    }

    @Override
    protected StringVerifier getVerifierFor(String value) {
        return new StringVerifier(value);
    }

    @Override
    protected String getValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected String getOtherValue() {
        return "otherValue";
    }

    @Test
    void isNotBlank() {
        assertAll(
                () -> {
                    StringVerifier verifier = new StringVerifier("  ");
                    assertThrows(IllegalArgumentException.class, verifier::isNotBlank);
                },
                () -> new StringVerifier(" abc ").isNotBlank()
        );
    }

    @Test
    void hasMinLength() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> verifier.hasMinLength(5)),
                () -> verifier.hasMinLength(4),
                () -> verifier.hasMinLength(3)
        );
    }

    @Test
    void hasMaxLength() {
        assertAll(
                () -> verifier.hasMaxLength(5),
                () -> verifier.hasMaxLength(4),
                () -> assertThrows(IllegalArgumentException.class, () -> verifier.hasMaxLength(3))
        );
    }

    @Test
    void hasLength() {
        assertAll(
                () -> verifier.hasLength(4),
                () -> assertThrows(IllegalArgumentException.class, () -> verifier.hasLength(5)),
                () -> assertThrows(IllegalArgumentException.class, () -> verifier.hasLength(3))
        );
    }

    @Test
    void combineWithSuperMethod() {
        assertDoesNotThrow(() ->
                verifier
                        .isNotNull()
                        .isNotBlank()
                        .isNotNull()
        );
    }

    @Test
    void germanErrorMessages(){
        Locale.setDefault(Locale.GERMAN);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> verifier.withArgumentName("utvalue").hasLength(3))
                .withMessage("Argument \"utvalue\" mit dem Wert \"asdf\" muss die Länge 3 haben");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> verifier.withArgumentName("utvalue").hasMaxLength(3))
                .withMessage("Argument \"utvalue\" mit dem Wert \"asdf\" darf die Maximallänge von 3 nicht überschreiten");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> verifier.withArgumentName("utvalue").hasMinLength(5))
                .withMessage("Argument \"utvalue\" mit dem Wert \"asdf\" muss eine Mindestlänge von 5 haben");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new StringVerifier("   ").withArgumentName("utvalue").isNotBlank())
                .withMessage("Argument \"utvalue\" mit dem Wert \"   \" darf nicht leer oder null sein");
    }
    @Test
    void englishErrorMessages(){
        Locale.setDefault(Locale.ENGLISH);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> verifier.withArgumentName("utvalue").hasLength(3))
                .withMessage("Argument \"utvalue\" with value \"asdf\" must have the length of 3");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> verifier.withArgumentName("utvalue").hasMaxLength(3))
                .withMessage("Argument \"utvalue\" with value \"asdf\" must have a maximum length of 3");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> verifier.withArgumentName("utvalue").hasMinLength(5))
                .withMessage("Argument \"utvalue\" with value \"asdf\" must have at least a length of 5");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new StringVerifier("   ").withArgumentName("utvalue").isNotBlank())
                .withMessage("Argument \"utvalue\" with value \"   \" must not be blank");
    }

}