package at.meks.validation;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static java.lang.String.format;

/**
 * This class provides all messages for validation errors. It was just centralized to be able to extend the message
 * creation later.
 *
 * The ideas are:
 * * extend by resource bundle to provide more languages
 * * extend by factory to be able to provide another message resolver
 */
public class ErrorMessageResolver {

    private static final String MUST_NOT_BE_BLANK = "mustn't be blank";
    private static final String VALUE_MUST_BE_NUMERIC = "value must be numeric";
    private static final String MUST_NOT_BE_NULL = "must not be null";
    private static final String LIST_MUST_NOT_BE_EMPTY = "list mustn't be empty";
    private static final String LIST_MUST_BE_EMPTY = "list must be empty";
    private static final String VALUE_MUST_BE_AN_INTEGER = "value must be an integer";
    private static final String VALUE_MUST_BE_A_BYTE = "value must be a byte";
    private static final String VALUE_MUST_BE_A_SHORT = "value must be a short";
    private static final String VALUE_MUST_BE_NULL = "value must be null";
    private static final String DATE_MUST_BE_THE_FIRST_DAY_OF_THE_YEAR = "Date must be the first day of the year";
    private static final String DATE_MUST_BE_THE_FIRST_DAY_OF_THE_MONTH = "Date must be the first day of the month";
    private static final String TIME_MUST_BE_THE_START_OF_THE_DAY = "Time must be 00:00:00";
    private static final String DATE_MUST_BE_THE_LAST_DATE_OF_THE_MONTH = "Date must be the last day of the month";

    public String getLengthIsMoreThanMessage(int size) {
        return format("must have more than %s chars", size);
    }

    public String getLengthIsLessThanMessage(int size) {
        return format("must have less than %s chars", size);
    }

    public String getHasLengthMessage(int length) {
        return format("length must be %s chars", length);
    }

    public String getContainsMessage(String contained) {
        return format("must contain %s", contained);
    }

    public String getIsNotBlankMessage() {
        return MUST_NOT_BE_BLANK;
    }

    public String getIsInListMessage(Collection<String> validValueSupplier) {
        return format("must be in list: [%s]", String.join(", ", validValueSupplier));
    }

    public String getIsDateMessage(DateTimeFormatter formatter) {
        return format("must match to date format %s", formatter);
    }

    public String getIsNumericMessage() {
        return VALUE_MUST_BE_NUMERIC;
    }

    public String getContainsNotOnlyMessage(String containedValue) {
        return format("value mustn't contain only %s", containedValue);
    }

    public String getNotNullMessage() {
        return MUST_NOT_BE_NULL;
    }

    public String getListContainsOnlyMessage(Object containedValue) {
        return format("list must contain only %s", containedValue);
    }

    public String getListContainsMessage(Object containedValue) {
        return format("list must contain %s", containedValue);
    }

    public String getListDoesNotContainMessage(Object excludedValue) {
        return format("list mustn't contain %s", excludedValue);
    }

    public String getListIsNotEmptyMessage() {
        return LIST_MUST_NOT_BE_EMPTY;
    }

    public String getListIsEmptyMessage() {
        return LIST_MUST_BE_EMPTY;
    }

    public String getListHasSizeMessage(int size) {
        return format("size of list must be %s", size);
    }

    public String getListHasMinSizeMessage(int size) {
        return format("size of list must be at least %s", size);
    }

    public String getListHasMaxSizeMessage(int size) {
        return format("size of list mustn't be greater than %s", size);
    }

    public String getIsLessThanMessage(Object compareTo) {
        return format("value must be less than %s", compareTo);
    }

    public String getIsGreaterThanMessage(Object compareTo) {
        return format("value must be greater than %s", compareTo);
    }

    public String getIsBetweenMessage(Object min, Object max) {
        return format("value must be between %s and %s", min, max);
    }

    public String getIsIntMessage() {
        return VALUE_MUST_BE_AN_INTEGER;
    }

    public String getIsByteMessage() {
        return VALUE_MUST_BE_A_BYTE;
    }

    public String getIsShortMessage() {
        return VALUE_MUST_BE_A_SHORT;
    }

    public String getIsEqualToMessage(Object comparedTo) {
        return format("value must be equal to %s", comparedTo);
    }

    public String getIsNotEqualToMessage(Object compareTo) {
        return format("value must not be equal to %s", compareTo);
    }

    public String getIsNullMessage() {
        return VALUE_MUST_BE_NULL;
    }

    public String getIsDateFirstDayOfYearMessage() {
        return DATE_MUST_BE_THE_FIRST_DAY_OF_THE_YEAR;
    }

    public String getIsDateFirstDayOfMonthMessage() {
        return DATE_MUST_BE_THE_FIRST_DAY_OF_THE_MONTH;
    }

    public String getIsTimeStartOfDayMessage() {
        return TIME_MUST_BE_THE_START_OF_THE_DAY;
    }

    public String getIsLastDayOfMonthMessage() {
        return DATE_MUST_BE_THE_LAST_DATE_OF_THE_MONTH;
    }
}