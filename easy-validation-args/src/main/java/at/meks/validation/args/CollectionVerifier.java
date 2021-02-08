package at.meks.validation.args;

import at.meks.validation.matcher.CollectionMatcher;

import java.util.Collection;

public class CollectionVerifier<T> extends AbstractVerifier<Collection<T>, CollectionVerifier<T>>{

    CollectionVerifier(Collection<T> argumentValue) {
        super(argumentValue);
    }

    /**
     * asserts that the validated colletion is empty.
     * @return following the Fluent-API the instance itself is returned
     */
    public CollectionVerifier<T> isEmpty() {
        assertMatcherReturnsTrue(CollectionMatcher::isEmpty);
        return this;
    }

    /**
     * asserts that the validated colletion is not empty.
     * @return following the Fluent-API the instance itself is returned
     */
    public CollectionVerifier<T> isNotEmpty() {
        assertMatcherReturnsTrue(CollectionMatcher::isNotEmpty);
        return this;
    }

    /**
     * asserts that the validated colletion contain only the expected values.
     * @return following the Fluent-API the instance itself is returned
     */
    @SafeVarargs
    public final CollectionVerifier<T> containsOnly (T firstContained, T... furtherContained) {
        assertMatcherReturnsTrue(value -> CollectionMatcher.containsOnly(value, firstContained, furtherContained));
        return this;
    }

    /**
     * asserts that the validated colletion contains the expected values. Other values are also allowed.
     * @return following the Fluent-API the instance itself is returned
     */
    @SafeVarargs
    public final CollectionVerifier<T> contains(T firstContained, T... furtherContained) {
        assertMatcherReturnsTrue(value -> CollectionMatcher.contains(value, firstContained, furtherContained));
        return this;
    }

}
