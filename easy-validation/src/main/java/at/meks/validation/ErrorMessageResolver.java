package at.meks.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static java.lang.String.format;

public class ErrorMessageResolver {

    private static final String MUSTN_T_BE_BLANK = "mustn't be blank";
    private static final String VALUE_MUST_BE_NUMERIC = "value must be numeric";
    private static final String MUST_NOT_BE_NULL = "must not be null";
    private static final String LIST_MUSTN_T_BE_EMPTY = "list mustn't be empty";
    private static final String LIST_MUST_BE_EMPTY = "list must be empty";

    public String getLengthIsMoreThanMessage(int size) {
        return format("must have more than %s chars", size);
    }

    public String getLengthIsLessThanMessage(int size) {
        return format("must have less than %s chars", size);
    }

    public String getHasLenghtMessage(int length) {
        return format("length must be %s chars", length);
    }

    public String getContainsMessage(String contained) {
        return format("must contain %s", contained);
    }

    public String getIsNotBlankMessage() {
        return MUSTN_T_BE_BLANK;
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

    public String getIsDateAfterMessage(LocalDateTime minDate) {
        return format("date must be after %s", minDate);
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
        return LIST_MUSTN_T_BE_EMPTY;
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
}