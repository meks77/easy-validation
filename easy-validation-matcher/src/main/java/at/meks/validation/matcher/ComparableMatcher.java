package at.meks.validation.matcher;

public class ComparableMatcher {

    public static <T extends Comparable<T>> boolean isGreater(T validatedValue, T comparedTo) {
        return ObjectMatcher.isNotNull(validatedValue) &&
                ObjectMatcher.isNotNull(comparedTo) &&
                validatedValue.compareTo(comparedTo) > 0;
    }

    public static <T extends Comparable<T>> boolean isGreaterOrEqual(T validatedValue, T comparedTo) {
        return ObjectMatcher.isNotNull(validatedValue) &&
                ObjectMatcher.isNotNull(comparedTo) &&
                validatedValue.compareTo(comparedTo) >= 0;
    }

    public static <T extends Comparable<T>> boolean isLess(T validatedValue, T comparedTo) {
        return !isGreaterOrEqual(validatedValue, comparedTo);
    }

    public static <T extends Comparable<T>> boolean isLessOrEqual(T validatedValue, T comparedTo) {
        return !isGreater(validatedValue, comparedTo);
    }

    public static <T extends Comparable<T>> boolean isBetween(T validatedValue, T min, T max) {
        return isGreaterOrEqual(validatedValue, min) && isLessOrEqual(validatedValue, max);
    }

    public static <T extends Comparable<T>> boolean isNotBetween(T validatedValue, T min, T max) {
        return !isBetween(validatedValue, min, max);
    }

}
