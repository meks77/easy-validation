package at.meks.validation.matcher;

import java.util.Arrays;
import java.util.Objects;

public class ObjectMatcher {

    private ObjectMatcher() {

    }

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
        if (isNull(aValue, otherValue)) {
            return true;
        }
        return aValue != null && aValue.equals(otherValue);
    }

    public static <T> boolean isNotEqual(T aValue, T otherValue) {
        return !isEqual(aValue, otherValue);
    }
}