package at.meks.validation.args;

import at.meks.validation.matcher.BooleanMatcher;

public class BooleanVerifier extends AbstractVerifier<Boolean, BooleanVerifier> {

    BooleanVerifier(Boolean argumentValue) {
        super(argumentValue);
    }

    /**
     * asserts that the validated value is equal to true.
     */
    public void isTrue() {
        assertMatcherReturnsTrue(BooleanMatcher::isTrue);
    }

    /**
     * asserts that the validated value is equal to false.
     */
    public void isFalse() {
        assertMatcherReturnsTrue(BooleanMatcher::isFalse);
    }

}
