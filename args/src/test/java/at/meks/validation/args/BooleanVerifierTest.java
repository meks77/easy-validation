package at.meks.validation.args;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BooleanVerifierTest extends AbstractVerifierTest<Boolean, BooleanVerifier> {

    private static final boolean VALIDATED_VALUE = true;
    private final BooleanVerifier verifierWithTrue = new BooleanVerifier(true);
    private final BooleanVerifier verifierWithFalse = new BooleanVerifier(false);

    @Override
    protected BooleanVerifier getVerifierFor(Boolean value) {
        return new BooleanVerifier(value);
    }

    @Override
    protected BooleanVerifier getVerifierWithValidatedValue() {
        return new BooleanVerifier(VALIDATED_VALUE);
    }

    @Override
    protected Boolean getValue() {
        return VALIDATED_VALUE;
    }

    @Override
    protected Boolean getOtherValue() {
        return false;
    }

    @Test
    void isTrue() {
        assertAll(
                verifierWithTrue::isTrue,
                () -> assertThrows(IllegalArgumentException.class, verifierWithFalse::isTrue)
        );
    }

    @Test
    void isFalse() {
        assertAll(
                verifierWithFalse::isFalse,
                () -> assertThrows(IllegalArgumentException.class, verifierWithTrue::isFalse)
        );
    }

}