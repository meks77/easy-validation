package at.meks.validation.args;

import at.meks.validation.core.Matcher;
import at.meks.validation.matcher.ObjectMatcher;

/**
 * This is the abstract class for all Verifiers.
 * It contains methods
 *   * needed by the implementing Verifiers
 *   * verification methods, each Verifier needs to have
 *
 * If a verification fails, allways an IllegalArgumentException is thrown.
 * @param <T>
 */
public abstract class AbstractVerifier<T, X extends AbstractVerifier<T, X>> {

    private final T argumentValue;

    protected AbstractVerifier(T argumentValue) {
        this.argumentValue = argumentValue;
    }

    /**
     * asserts using a custom matcher.
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

    /**
     * asserts that the validated value is not null.
     * @return  the current verifier
     */
    public X isNotNull() throws IllegalArgumentException {
        assertMatcherReturnsTrue(ObjectMatcher::isNotNull);
        return (X) this;
    }

    /**
     * asserts that the validated value is null.
     * @return  the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNull() throws IllegalArgumentException {
        assertMatcherReturnsTrue(ObjectMatcher::isNull);
        return (X) this;
    }

    /**
     * asserts that the validated value is equal to the provided argument.
     * @param otherValue    the validated value is compared to this
     * @return  the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isEqualTo(T otherValue) throws IllegalArgumentException {
        assertMatcherReturnsTrue(value -> ObjectMatcher.isEqual(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that the validated value is not equal to the provided
     * @param otherValue the validated value is compared to this arg
     * @return the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNotEqualTo(T otherValue) throws IllegalArgumentException {
        assertMatcherReturnsTrue(value -> ObjectMatcher.isNotEqual(value, otherValue));
        return (X) this;
    }

}
