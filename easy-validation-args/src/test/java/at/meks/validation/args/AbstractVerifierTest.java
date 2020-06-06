package at.meks.validation.args;

import at.meks.validation.core.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractVerifierTest<T, V extends AbstractVerifier<T, V>> {

    private final V verifierWithValue = getVerifierWithValidatedValue();
    private final V verifierWithNullValue = getVerifierWithNullValue();

    protected abstract V getVerifierWithValidatedValue();

    protected abstract V getVerifierWithNullValue();

    protected abstract T getValidatedValue();

    protected abstract T getOtherValue();

    @Test
    final void matches() {
        @SuppressWarnings("unchecked")
        Matcher<T> matcher = Mockito.mock(Matcher.class);
        Mockito.when(matcher.verify(getValidatedValue())).thenReturn(true);

        getVerifierWithValidatedValue().matches(matcher);

        Mockito.verify(matcher).verify(getValidatedValue());
    }

    @Test
    final void assertMatcherReturnsTrue() {
        @SuppressWarnings("unchecked")
        Matcher<T> matcher = Mockito.mock(Matcher.class);
        Mockito.when(matcher.verify(getValidatedValue())).thenReturn(true, false);
        assertAll(
                () -> verifierWithValue.assertMatcherReturnsTrue(matcher),
                () -> assertThrows(IllegalArgumentException.class,
                            () -> verifierWithValue.assertMatcherReturnsTrue(matcher))
        );
    }

    @Test
    final void isNotNull() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, verifierWithNullValue::isNotNull),
                verifierWithValue::isNotNull
        );
    }

    @Test
    final void isNull() {
        assertAll(
                verifierWithNullValue::isNull,
                () -> assertThrows(IllegalArgumentException.class, verifierWithValue::isNull)
        );
    }

    @Test
    final void isEqualTo() {
        T otherValue = getOtherValue();
        assertAll(
                () -> verifierWithValue.isEqualTo(getValidatedValue()),
                () ->  assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isEqualTo(otherValue))
        );
    }

    @Test
    final void isNotEqualTo() {
        T validatedValue = getValidatedValue();
        assertAll(
                () -> verifierWithValue.isNotEqualTo(getOtherValue()),
                () -> assertThrows(IllegalArgumentException.class, () -> verifierWithValue.isNotEqualTo(validatedValue))
        );
    }

}