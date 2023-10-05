package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringVerifierTest extends AbstractVerifierTest<String, StringVerifier> {

    private static final String VALIDATED_VALUE = "validatedValue";

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
        StringVerifier verifier = new StringVerifier("asdf");
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> verifier.hasMinLength(5)),
                () -> verifier.hasMinLength(4),
                () -> verifier.hasMinLength(3)
        );
    }

    @Test
    void combineWithSuperMethod() {
        assertDoesNotThrow(() ->
            new StringVerifier("asdf")
                    .isNotNull()
                    .isNotBlank()
                    .isNotNull()
        );
    }

}