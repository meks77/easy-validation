package at.meks.validation.args;

import at.meks.validation.args.errormessage.ErrorMessage;
import at.meks.validation.args.errormessage.ErrorMessageBuilder;
import at.meks.validation.core.Matcher;
import at.meks.validation.matcher.ObjectMatcher;

import java.util.function.Supplier;

/**
 * This is the abstract class for all Verifiers.
 * It contains methods
 *   * needed by the implementing Verifiers
 *   * abstract verification methods, each Verifier has to implement
 * <p>
 * If verification fails, an IllegalArgumentException is thrown.
 * </p>
 * @param <T> the type of the value, the verifier is for
 */
public abstract class AbstractVerifier<T, X extends AbstractVerifier<T, X>> {

    private final T argumentValue;
    private final ErrorMessageBuilder errorMessageBuilder = new ErrorMessageBuilder();

    protected AbstractVerifier(T argumentValue) {
        this.argumentValue = argumentValue;
    }

    /**
     * asserts using a custom matcher.
     * @param matcher   is used to verify if the value matches the verification
     * @return itself for use with the fluent API
     */
    public X matches(Matcher<T> matcher) {
        assertMatcherReturnsTrue(matcher);
        return (X) this;
    }

    void assertMatcherReturnsTrue(Matcher<T> matcher) {
        if (!matcher.verify(argumentValue)) {
            errorMessageBuilder.withArgumentValue(argumentValue);
            throw errorMessageBuilder.build()
                    .map(ErrorMessage::asText)
                    .map(IllegalArgumentException::new)
                    .orElseGet(IllegalArgumentException::new);
        }
    }

    /**
     * asserts that the validated value is not null.
     * @return  the current verifier
     */
    public X isNotNull() {
        withMessageKey("isNotNull");
        assertMatcherReturnsTrue(ObjectMatcher::isNotNull);
        return (X) this;
    }

    /**
     * asserts that the validated value is null.
     * @return  the current verifier
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNull() {
        withMessageKey("isNull");
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
        withMessageKey("isEqualTo");
        withFurtherMessageFormatArg(argumentValue);
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
        withMessageKey("isNotEqualTo");
        withFurtherMessageFormatArg(otherValue);
        assertMatcherReturnsTrue(value -> ObjectMatcher.isNotEqual(value, otherValue));
        return (X) this;
    }

    public X withMessage(Supplier<String> errorMessage) {
        errorMessageBuilder.withMessageSupplier(errorMessage);
        return (X) this;
    }

    public X withArgumentName(String argumentName) {
        errorMessageBuilder.withArgumentName(argumentName);
        return (X) this;
    }

    protected void withMessageKey(String errorMessageKey) {
        errorMessageBuilder.withMessageBundleKey(errorMessageKey);
    }

    protected void withFurtherMessageFormatArg(T value) {
        errorMessageBuilder.withFurtherMessageFormatArg(value);
    }

}
