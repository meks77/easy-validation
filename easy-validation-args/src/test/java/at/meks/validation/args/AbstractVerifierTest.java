package at.meks.validation.args;

import at.meks.validation.core.Matcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractVerifierTest<T, V extends AbstractVerifier<T, V>> {

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
                () -> getVerifierWithValidatedValue().assertMatcherReturnsTrue(matcher),
                () -> assertThrows(IllegalArgumentException.class,
                            () -> getVerifierWithValidatedValue().assertMatcherReturnsTrue(matcher))
        );
    }

    @Test
    final void isNotNull() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                            () -> getVerifierWithNullValue().isNotNull()),
                () -> getVerifierWithValidatedValue().isNotNull()
        );
    }

    @Test
    final void isNull() {
        assertAll(
                () -> getVerifierWithNullValue().isNull(),
                () -> assertThrows(IllegalArgumentException.class,
                            () -> getVerifierWithValidatedValue().isNull())
        );
    }

    @Test
    final void isEqualTo() {
        assertAll(
                () -> getVerifierWithValidatedValue().isEqualTo(getValidatedValue()),
                () ->  assertThrows(IllegalArgumentException.class,
                            () -> getVerifierWithValidatedValue().isEqualTo(getOtherValue()))
        );
    }

    @Test
    final void isNotEqualTo() {
        assertAll(
                () -> getVerifierWithValidatedValue().isNotEqualTo(getOtherValue()),
                () -> assertThrows(IllegalArgumentException.class,
                            () -> getVerifierWithValidatedValue().isNotEqualTo(getValidatedValue()))
        );
    }

}