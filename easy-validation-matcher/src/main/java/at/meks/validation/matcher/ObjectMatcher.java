package at.meks.validation.matcher;

import java.util.Arrays;
import java.util.Objects;

public class ObjectMatcher {

    @SafeVarargs
    public static <T> boolean isNull(T... furtherValues) {
        return furtherValues == null ||
                Arrays.stream(furtherValues)
                        .noneMatch(Objects::nonNull);
    }

    @SafeVarargs
    public static <T> boolean isNotNull(T... furtherValues) {
        return furtherValues != null &&
                Arrays.stream(furtherValues)
                        .noneMatch(Objects::isNull);
    }

    public static <T> boolean isEqual(T aValue, T otherValue) {
        return isNull(aValue, otherValue) ||
                (aValue != null && aValue.equals(otherValue)) ||
                (otherValue != null && otherValue.equals(aValue));
    }

    public static <T> boolean isNotEqual(T aValue, T otherValue) {
        return !isEqual(aValue, otherValue);
    }
}