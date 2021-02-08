package at.meks.validation.args;

import at.meks.validation.core.Matcher;
import at.meks.validation.matcher.ObjectMatcher;

import java.util.function.Supplier;

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
    private Supplier<String> errorMessage;

    protected AbstractVerifier(T argumentValue) {
        this.argumentValue = argumentValue;
    }

    /**
     * asserts using a custom matcher.
     * @param matcher   is used to verify if the value matches the verification
     */
    public X matches(Matcher<T> matcher) {
        assertMatcherReturnsTrue(matcher);
        return (X) this;
    }

    void assertMatcherReturnsTrue(Matcher<T> matcher) {
        if (!matcher.verify(argumentValue)) {
            if (errorMessage != null) {
                throw new IllegalArgumentException(errorMessage.get());
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * asserts that the validated value is not null.
     * @return  the current verifier
     */
    public X isNotNull() {
        assertMatcherReturnsTrue(ObjectMatcher::isNotNull);
        return (X) this;
    }

    /**
     * asserts that the validated value is null.
     * @return  the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNull() {
        assertMatcherReturnsTrue(ObjectMatcher::isNull);
        return (X) this;
    }

    /**
     * asserts that the validated value is equal to the provided argument.
     * @param otherValue    the validated value is compared to this
     * @return  the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isEqualTo(T otherValue) {
        assertMatcherReturnsTrue(value -> ObjectMatcher.isEqual(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that the validated value is not equal to the provided
     * @param otherValue the validated value is compared to this arg
     * @return the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNotEqualTo(T otherValue) {
        assertMatcherReturnsTrue(value -> ObjectMatcher.isNotEqual(value, otherValue));
        return (X) this;
    }

    public X withMessage(Supplier<String> errorMessage) {
        this.errorMessage = errorMessage;
        return (X) this;
    }
}
