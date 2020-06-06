package at.meks.validation.args;

import at.meks.validation.core.Matcher;
import at.meks.validation.matcher.ObjectMatcher;

/**
 * This is the abstract class for all Verifiers.
 * It contains methods
 *   * needed by the implementing Verifiers
 *   * verification methods, each Verifier needs to have
 *
 * If a verification fails, allways an IllegalArgumentException must be thrown.
 * @param <T>
 */
public abstract class AbstractVerifier<T, X extends AbstractVerifier<T, X>> {

    private final T argumentValue;

    protected AbstractVerifier(T argumentValue) {
        this.argumentValue = argumentValue;
    }

    /**
     * verification using a custom matcher.
     * @param matcher   is used to verify if the value matches the verification
     */
    public void matches(Matcher<T> matcher) {
        assertMatcherReturnsTrue(matcher);
    }

    void assertMatcherReturnsTrue(Matcher<T> matcher) throws IllegalArgumentException{
        if (!matcher.verify(argumentValue)) {
            throw new IllegalArgumentException();
        }
    }

    public X isNotNull() {
        assertMatcherReturnsTrue(ObjectMatcher::isNotNull);
        return (X) this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public X isNull() {
        assertMatcherReturnsTrue(ObjectMatcher::isNull);
        return (X) this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public X isEqualTo(T otherValue) {
        assertMatcherReturnsTrue(value -> ObjectMatcher.isEqual(value, otherValue));
        return (X) this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public X isNotEqualTo(T otherValue) {
        assertMatcherReturnsTrue(value -> ObjectMatcher.isNotEqual(value, otherValue));
        return (X) this;
    }

}
