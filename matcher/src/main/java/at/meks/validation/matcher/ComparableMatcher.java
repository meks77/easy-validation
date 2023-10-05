package at.meks.validation.matcher;

public class ComparableMatcher {

    private ComparableMatcher() {
    }

    public static <T extends Comparable<T>> boolean isGreater(T validatedValue, T comparedTo) {
        return ObjectMatcher.isNotNull(validatedValue, comparedTo) &&
                validatedValue.compareTo(comparedTo) > 0;
    }

    public static <T extends Comparable<T>> boolean isGreaterOrEqual(T validatedValue, T comparedTo) {
        return ObjectMatcher.isNotNull(validatedValue, comparedTo) &&
                validatedValue.compareTo(comparedTo) >= 0;
    }

    public static <T extends Comparable<T>> boolean isLess(T validatedValue, T comparedTo) {
        return ObjectMatcher.isNotNull(validatedValue, comparedTo) &&
                !isGreaterOrEqual(validatedValue, comparedTo);
    }

    public static <T extends Comparable<T>> boolean isLessOrEqual(T validatedValue, T comparedTo) {
        return ObjectMatcher.isNotNull(validatedValue, comparedTo) &&
                !isGreater(validatedValue, comparedTo);
    }

    public static <T extends Comparable<T>> boolean isBetween(T validatedValue, T min, T max) {
        return ObjectMatcher.isNotNull(validatedValue, min, max) &&
                isGreaterOrEqual(validatedValue, min) &&
                isLessOrEqual(validatedValue, max);
    }

    public static <T extends Comparable<T>> boolean isNotBetween(T validatedValue, T min, T max) {
        return ObjectMatcher.isNotNull(validatedValue, min, max) &&
                !isBetween(validatedValue, min, max);
    }

}
