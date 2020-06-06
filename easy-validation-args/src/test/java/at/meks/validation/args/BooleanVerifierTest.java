package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BooleanVerifierTest extends AbstractVerifierTest<Boolean, BooleanVerifier> {

    private static final boolean VALIDATED_VALUE = true;

    @Override
    protected BooleanVerifier getVerifierWithValidatedValue() {
        return new BooleanVerifier(VALIDATED_VALUE);
    }

    @Override
    protected BooleanVerifier getVerifierWithNullValue() {
        return new BooleanVerifier(null);
    }

    @Override
    protected Boolean getValidatedValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected Boolean getOtherValue() {
        return false;
    }

    @Test
    void isTrue() {
        assertAll(
                () -> new BooleanVerifier(true).isTrue(),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new BooleanVerifier(false).isTrue())
        );
    }

    @Test
    void isFalse() {
        assertAll(
                () -> new BooleanVerifier(false).isFalse(),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> new BooleanVerifier(true).isFalse())
        );
    }

}