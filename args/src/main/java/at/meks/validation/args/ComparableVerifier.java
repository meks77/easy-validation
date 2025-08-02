package at.meks.validation.args;

import at.meks.validation.matcher.ComparableMatcher;
import at.meks.validation.matcher.ObjectMatcher;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ComparableVerifier<T extends Comparable<T>, X extends ComparableVerifier<T, X>> extends AbstractVerifier<T, X> {

    ComparableVerifier(T argumentValue) {
        super(argumentValue);
    }

    /**
     * asserts that the validated is greater than another value.
     * @param otherValue used to compare to
     * @return itself for use with the fluent API
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isGreater(T otherValue) {
        withMessageKey("comparable.isGreater");
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isGreater(value, otherValue));
        return (X) this;
    }

    @SafeVarargs
    private boolean valuesAreNotNull(T value1, T... furthervalues) {
        var values = new ArrayList<>();
        values.add(value1);
        values.addAll(Stream.of(furthervalues)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        return values.stream().allMatch(ObjectMatcher::isNotNull);
    }

    /**
     * asserts that the validated is greater or equal to another value.
     * @param otherValue used to compare to
     * @return itself for use with the fluent API
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isGreaterOrEqual(T otherValue) {
        withMessageKey("comparable.isGreaterOrEqual");
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isGreaterOrEqual(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that the validated is less than another value.
     * @param otherValue used to compare to
     * @return itself for use with the fluent API
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isLess(T otherValue) {
        withMessageKey("comparable.isLess");
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isLess(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that the validated is less or equal to another value.
     * @param otherValue used to compare to
     * @return itself for use with the fluent API
     */
    @SuppressWarnings("UnusedReturnValue")
        public X isLessOrEqual(T otherValue) {
        withMessageKey("comparable.isLessOrEqual");
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, otherValue) && ComparableMatcher.isLessOrEqual(value, otherValue));
        return (X) this;
    }

    /**
     * asserts that a value is between two other values.
     * @param min min value including
     * @param max max value including
     * @return itself for use with the fluent API
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isBetween(T min, T max) {
        withMessageKey("comparable.isBetween");
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, min, max) && ComparableMatcher.isBetween(value, min, max));
        return (X) this;
    }

    /**
     * asserts that a value is not between two other values.
     * @param min min value including
     * @param max max value including
     * @return itself for use with the fluent API
     */
    @SuppressWarnings("UnusedReturnValue")
    public X isNotBetween(T min, T max) {
        withMessageKey("comparable.isNotBetween");
        assertMatcherReturnsTrue(
                value -> valuesAreNotNull(value, min, max) && ComparableMatcher.isNotBetween(value, min, max));
        return (X) this;
    }

}
