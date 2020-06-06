package at.meks.validation.matcher;

import static at.meks.validation.matcher.ObjectMatcher.isNotNull;
import static at.meks.validation.matcher.ObjectMatcher.isNull;

public class StringMatcher {

    private StringMatcher() {

    }

    public static boolean isNotBlank(String value) {
        return isNotNull(value) &&
                !value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return isNotNull(value) &&
                !value.isEmpty();
    }

    public static boolean contains(String value, String containing) {
        if (isNull(value) && isNull(containing)) {
            return true;
        } else if (isNull(value) || isNull(containing)) {
            return false;
        } else {
            return value.contains(containing);
        }
    }

    public static boolean containsNot(String value, String containing) {
        return !contains(value, containing);
    }

    public static boolean containsDigitsOnly(String value) {
        return isNotNull(value) && value.matches("\\d+");
    }

    public static boolean hasMinLength(String value, int minLength) {
        return isNotNull(value) && value.length() >= minLength;
    }
}
