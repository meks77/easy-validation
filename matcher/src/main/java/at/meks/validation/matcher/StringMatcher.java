package at.meks.validation.matcher;

import static at.meks.validation.matcher.ObjectMatcher.isNotNull;
import static at.meks.validation.matcher.ObjectMatcher.isNull;

public class StringMatcher {

    private StringMatcher() {
    }

    /**
     * Checks if a given string value is not blank.
     *
     * @param value The string value to check.
     * @return true if the value is not null and not empty after trimming whitespace, false otherwise.
     */
    public static boolean isNotBlank(String value) {
        return isNotNull(value) &&
                !value.trim().isEmpty();
    }

    /**
     * Checks if a given string value is not empty.
     *
     * @param value The string value to check.
     * @return true if the value is not null and not empty, false otherwise.
     */
    public static boolean isNotEmpty(String value) {
        return isNotNull(value) &&
                !value.isEmpty();
    }

    /**
     * Checks if a given string value contains another string.
     *
     * @param value     The string value to check.
     * @param containing The string to check if it is contained in the value.
     * @return true if the value is not null and contains the specified "containing" string, false otherwise.
     */
    public static boolean contains(String value, String containing) {
        if (isNull(value) && isNull(containing)) {
            return true;
        } else if (isNull(value) || isNull(containing)) {
            return false;
        } else {
            return value.contains(containing);
        }
    }

    /**
     * Checks if a given string value does not contain another string.
     *
     * @param value     The string value to check.
     * @param containing The string to check if it is not contained in the value.
     * @return {@code true} if the value is not null and does not contain the specified "containing" string, {@code false} otherwise.
     */
    public static boolean containsNot(String value, String containing) {
        return !contains(value, containing);
    }

    /**
     * Checks if a given string value contains only digits.
     *
     * @param value The string value to check.
     * @return {@code true} if the value is not null and contains only digits, {@code false} otherwise.
     */
    public static boolean containsDigitsOnly(String value) {
        return isNotNull(value) && value.matches("\\d+");
    }

    /**
     * Checks if a given string value has a minimum length.
     *
     * @param value    The string value to check.
     * @param minLength The minimum length required for the value.
     * @return {@code true} if the value is not null and its length is greater than or equal to the minimum length,
     *         {@code false} otherwise.
     */
    public static boolean hasMinLength(String value, int minLength) {
        return isNotNull(value) && value.length() >= minLength;
    }

    /**
     * Checks if a given string value has a maximum length.
     *
     * @param value     The string value to check.
     * @param maxLength The maximum length allowed for the value.
     * @return {@code true} if the value is not null and its length is less than or equal to the maximum length,
     *         {@code false} otherwise.
     */
    public static boolean hasMaxLength(String value, int maxLength) {
        return isNotNull(value) && value.length() <= maxLength;
    }

    /**
     * Checks if a given string value has a specific length.
     *
     * @param value  The string value to check.
     * @param length The desired length for the value.
     * @return {@code true} if the value is not null and its length is equal to the specified length,
     *         {@code false} otherwise.
     */
    public static boolean hasLength(String value, int length) {
        return isNotNull(value) && value.length() == length;
    }
}
