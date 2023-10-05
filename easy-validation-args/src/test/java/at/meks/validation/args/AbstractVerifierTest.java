package at.meks.validation.args;

import at.meks.validation.core.Matcher;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Locale;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractVerifierTest<T, V extends AbstractVerifier<T, V>> {

    private final V verifierWithValue = getVerifierWithValidatedValue();
    private final V verifierWithNullValue = getVerifierWithNullValue();

    protected abstract V getVerifierWithValidatedValue();

    protected V getVerifierWithNullValue() {
        return getVerifierFor(null);
    }

    protected abstract V getVerifierFor(T value);

    protected abstract T getValue();

    protected abstract T getOtherValue();

    @Test
    final void matches() {
        @SuppressWarnings("unchecked")
        Matcher<T> matcher = Mockito.mock(Matcher.class);
        Mockito.when(matcher.verify(getValue())).thenReturn(true);

        getVerifierWithValidatedValue().matches(matcher);

        Mockito.verify(matcher).verify(getValue());
    }

    @Test
    final void assertMatcherReturnsTrue() {
        @SuppressWarnings("unchecked")
        Matcher<T> matcher = Mockito.mock(Matcher.class);
        Mockito.when(matcher.verify(getValue())).thenReturn(true, false);
        assertAll(
                () -> verifierWithValue.assertMatcherReturnsTrue(matcher),
                () -> assertExceptionWithoutMessage(() -> verifierWithValue.assertMatcherReturnsTrue(matcher))
        );
    }

    private void assertExceptionWithoutMessage(Runnable invokedMethod) {
        assertThrows(IllegalArgumentException.class, invokedMethod::run);
    }

    @Nested
    class WhenIsNotNull extends VerificationTests<T, V> {

        @Override
        protected V getVerifierWithValidValue() {
            return verifierWithValue;
        }

        @Override
        protected void invokeVerificationMethod(V v) {
            v.isNotNull();
        }

        @Override
        protected V getVerifierWithInvalidValue() {
            return verifierWithNullValue;
        }

        @Override
        protected String getExpectedErrorMessage(String argumentName, Locale language) {
            if (language.equals(Locale.GERMAN)) {
                return formatErrorMessage(
                        "Argument \"%s\" darf nicht null sein",
                        argumentName, getOtherValue());
            }
            return formatErrorMessage(
                    "Argument \"%s\" mustn't be null",
                    argumentName, getOtherValue());
        }

    }

    @Nested
    class WhenIsNull extends VerificationTests<T, V> {

        @Override
        protected void invokeVerificationMethod(V verifier) {
            verifier.isNull();
        }

        @Override
        protected V getVerifierWithValidValue() {
            return getVerifierWithNullValue();
        }

        @Override
        protected V getVerifierWithInvalidValue() {
            return verifierWithValue;
        }

        @Override
        protected String getExpectedErrorMessage(String argumentName, Locale language) {
            if (language.equals(Locale.GERMAN)) {
                return formatErrorMessage(
                        "Argument \"%s\" mit dem Wert \"%s\" muss null sein",
                        argumentName, getValue());
            }
            return formatErrorMessage(
                    "Argument \"%s\" with value \"%s\" must be null",
                    argumentName, getValue());
        }

    }

    @Nested
    class WhenIsEqualTo extends VerificationTests<T, V> {

        @Override
        protected V getVerifierWithValidValue() {
            return getVerifierFor(getValue());
        }

        @Override
        protected void invokeVerificationMethod(V v) {
            v.isEqualTo(getValue());
        }

        @Override
        protected V getVerifierWithInvalidValue() {
            return getVerifierFor(getOtherValue());
        }

        @Override
        protected String getExpectedErrorMessage(String argumentName, Locale language) {
            if (language.equals(Locale.GERMAN)) {
                return formatErrorMessage("Argument \"%s\" mit dem Wert \"%s\" muss \"%s\" entsprechen", argumentName, AbstractVerifierTest.this.getOtherValue());
            }
            return formatErrorMessage("Argument \"%s\" with value \"%s\" must be equal to \"%s\"", argumentName, AbstractVerifierTest.this.getOtherValue());
        }
    }

    @Nested
    class WhenIsNotEqualTo extends VerificationTests<T, V> {

        @Override
        protected V getVerifierWithValidValue() {
            return getVerifierFor(getValue());
        }

        @Override
        protected void invokeVerificationMethod(V v) {
            v.isNotEqualTo(getOtherValue());
        }

        @Override
        protected V getVerifierWithInvalidValue() {
            return getVerifierFor(getOtherValue());
        }

        @Override
        protected String getExpectedErrorMessage(String argumentName, Locale language) {
            if (language.equals(Locale.GERMAN)) {
                return formatErrorMessage(
                        "Argument \"%s\" mit dem Wert \"%s\" darf nicht \"%s\" entsprechen",
                        argumentName, getOtherValue());
            }
            return formatErrorMessage(
                    "Argument \"%s\" with value \"%s\" must not be equal to \"%s\"",
                    argumentName, getOtherValue());
        }
    }

    private String formatErrorMessage(String message, String argumentName, T validatedValue) {
        return format(message,
                argumentName, validatedValue, getOtherValue());
    }

}