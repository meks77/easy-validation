package at.meks.validation.args;

import at.meks.validation.matcher.ComparableMatcher;
import at.meks.validation.matcher.ObjectMatcher;

import java.util.Arrays;

public abstract class ComparableVerifier<T extends Comparable<T>, X extends ComparableVerifier<T, X>> extends AbstractVerifier<T, X> {

    ComparableVerifier(T argumentValue) {
        super(argumentValue);
    }

    /**
     * asserts that the validated is greater than another value.
     * @param otherValue used to compare to
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isGreater(T otherValue) {
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isGreater(value, otherValue));
        return (X) this;
    }

    @SafeVarargs
    private final boolean valuesAreNotNull(T firstValue, T... otherValues) {
        return ObjectMatcher.isNotNull(firstValue) &&
                (otherValues != null &&
                        Arrays.stream(otherValues).allMatch(ObjectMatcher::isNotNull));
    }

    /**
     * asserts that the validated is greater or equal to another value.
     * @param otherValue used to compare to
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isGreaterOrEqual(T otherValue) {
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isGreaterOrEqual(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that the validated is less than another value.
     * @param otherValue used to compare to
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isLess(T otherValue) {
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isLess(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that the validated is less or equal to another value.
     * @param otherValue used to compare to
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isLessOrEqual(T otherValue) {
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isLessOrEqual(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that a value is between two other values.
     * @param min min value including
     * @param max max value including
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isBetween(T min, T max) {
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, min, max) && ComparableMatcher.isBetween(value, min, max));
        return (X) this;
    }

    /**
     * asserts that a value is not between two other values.
     * @param min min value including
     * @param max max value including
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNotBetween(T min, T max) {
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, min, max) && ComparableMatcher.isNotBetween(value, min, max));
        return (X) this;
    }

}
