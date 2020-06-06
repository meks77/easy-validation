package at.meks.validation.args;

import at.meks.validation.matcher.CollectionMatcher;

import java.util.Collection;

public class CollectionVerifier<T> extends AbstractVerifier<Collection<T>, CollectionVerifier<T>>{

    CollectionVerifier(Collection<T> argumentValue) {
        super(argumentValue);
    }

    /**
     * asserts that the validated colletion is empty.
     */
    public void isEmpty() throws IllegalArgumentException {
        assertMatcherReturnsTrue(CollectionMatcher::isEmpty);
    }

    /**
     * asserts that the validated colletion is not empty.
     */
    public void isNotEmpty() throws IllegalArgumentException {
        assertMatcherReturnsTrue(CollectionMatcher::isNotEmpty);
    }

    /**
     * asserts that the validated colletion contain only the expected values.
     */
    @SafeVarargs
    public final void containsOnly (T firstContained, T... furtherContained) throws IllegalArgumentException {
        assertMatcherReturnsTrue(value -> CollectionMatcher.containsOnly(value, firstContained, furtherContained));
    }

    /**
     * asserts that the validated colletion contains the expected values. Other values are also allowed.
     */
    @SafeVarargs
    public final void contains(T firstContained, T... furtherContained) throws IllegalArgumentException {
        assertMatcherReturnsTrue(value -> CollectionMatcher.contains(value, firstContained, furtherContained));
    }

}
