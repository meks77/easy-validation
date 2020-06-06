package at.meks.validation.matcher;

import java.util.*;

public class CollectionMatcher {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return ObjectMatcher.isNull(collection) ||
                collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    @SafeVarargs
    public static <T> boolean containsOnly(Collection<T> collection, T firstContained, T...furtherContained) {
        Set<T> containedValues = toSet(firstContained, furtherContained);
        return collection.containsAll(containedValues);
    }

    @SafeVarargs
    public static <T> boolean contains(Collection<T> collection, T firstContained, T...furtherContained) {
        Set<T> contained = toSet(firstContained, furtherContained);
        return contained.containsAll(collection);
    }

    private static <T> Set<T> toSet(T firstContained, T[] furtherContained) {
        Set<T> containedValues = new HashSet<>();
        containedValues.add(firstContained);
        if (furtherContained != null) {
            containedValues.addAll(Arrays.asList(furtherContained));
        }
        return containedValues;
    }
}
