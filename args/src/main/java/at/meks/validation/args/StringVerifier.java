package at.meks.validation.args;

import at.meks.validation.matcher.StringMatcher;

/**
 * This class provides verifications for strings.
 */
public class StringVerifier extends ComparableVerifier<String, StringVerifier> {

    StringVerifier(String argumentValue) {
        super(argumentValue);
    }

    /**
     * asserts that the validated string is not blank.<br>
     * Examples:<br>
     * "" throws exception<br>
     * " " throws exception<br>
     * null throws exception<br>
     * " a " valid<br>
     * @return the current instance of the verifier
     * @throws IllegalArgumentException if the string is blank
     */
    public StringVerifier isNotBlank() {
        withMessageKey("string.isNotBlank");
        assertMatcherReturnsTrue(StringMatcher::isNotBlank);
        return this;
    }

    /**
     * asserts the the validated string has at least the lenght provided by the argument.
     * @param minLength the minimum length the validated string must have
     * @return  the current instance of the verifier
     * @throws IllegalArgumentException if the string is violates the minimum length
     */
    public StringVerifier hasMinLength(int minLength) {
        withMessageKey("string.hasMinLength");
        assertMatcherReturnsTrue(value -> StringMatcher.hasMinLength(value, minLength));
        return this;
    }


    /**
     * Asserts that the validated string has a maximum length provided by the argument.
     * @param maxLength the maximum length the validated string must have
     * @return the current instance of the StringVerifier
     * @throws IllegalArgumentException if the string violates the maximum length
     */
    public StringVerifier hasMaxLength(int maxLength) {
        withMessageKey("string.hasMaxLength");
        assertMatcherReturnsTrue(value -> StringMatcher.hasMaxLength(value, maxLength));
        return this;
    }

    /**
     * Asserts that the validated string has the specified length.
     * @param length the length the validated string must have
     * @return the current instance of the StringVerifier
     * @throws IllegalArgumentException if the string length is not equal to the specified length
     */
    public StringVerifier hasLength(int length) {
        withMessageKey("string.hasLength");
        assertMatcherReturnsTrue(value -> StringMatcher.hasLength(value, length));
        return this;
    }
}
