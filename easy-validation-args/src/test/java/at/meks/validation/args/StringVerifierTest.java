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
    protected StringVerifier getVerifierWithNullValue() {
        return new StringVerifier(null);
    }

    @Override
    protected String getValidatedValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected String getOtherValue() {
        return "otherValue";
    }

    @Test
    void isNotBlank() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                            () -> new StringVerifier("  ").isNotBlank()),
                () -> new StringVerifier(" abc ").isNotBlank()
        );
    }

    @Test
    void hasMinLength() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                            () -> new StringVerifier("asdf").hasMinLength(5)),
                () -> new StringVerifier("asdf").hasMinLength(4),
                () -> new StringVerifier("asdf").hasMinLength(3)
        );
    }

    @Test
    void combineWithSuperMethod() {
        new StringVerifier("asdf")
                .isNotNull()
                .isNotBlank()
                .isNotNull();
    }

}