package at.meks.validation.args;

import at.meks.validation.matcher.CollectionMatcher;

import java.util.Collection;

public class CollectionVerifier<T> extends AbstractVerifier<Collection<T>, CollectionVerifier<T>>{

    CollectionVerifier(Collection<T> argumentValue) {
        super(argumentValue);
    }

    /**
     * the validated colletion will be asserted to be empty.
     */
    public void isEmpty() {
        assertMatcherReturnsTrue(CollectionMatcher::isEmpty);
    }

    /**
     * the validated colletion will be asserted to be not empty.
     */
    public void isNotEmpty() {
        assertMatcherReturnsTrue(CollectionMatcher::isNotEmpty);
    }

    /**
     * the validated colletion will be asserted to contain only the expected values.
     */
    @SafeVarargs
    public final void containsOnly(T firstContained, T... furtherContained) {
        assertMatcherReturnsTrue(value -> CollectionMatcher.containsOnly(value, firstContained, furtherContained));
    }

    /**
     * the validated colletion will be asserted to contain the expected values. Other values are also allowed.
     */
    @SafeVarargs
    public final void contains(T firstContained, T... furtherContained) {
        assertMatcherReturnsTrue(value -> CollectionMatcher.contains(value, firstContained, furtherContained));
    }

}
