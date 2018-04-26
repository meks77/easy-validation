package org.meks.validation;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static java.lang.String.format;

class ErrorMessageResolver {

    private static final String MUSTN_T_BE_BLANK = "mustn't be blank";
    private static final String VALUE_MUST_BE_NUMERIC = "value must be numeric";

    private ErrorMessageResolver() {}

    static String getLengthIsMoreThanMessage(int size) {
        return format("must have more than %s chars", size);
    }

    static String getLenghtIsLessThanMessage(int size) {
        return format("must have less than %s chars", size);
    }

    static String getHasLenghtMessage(int length) {
        return format("length must be %s chars", length);
    }

    static String getContainsMessage(String contained) {
        return format("must contain %s", contained);
    }

    static String getIsNotBlankMessage() {
        return MUSTN_T_BE_BLANK;
    }

    static String getIsInListMessage(Collection<String> validValueSupplier) {
        return format("must be in list: [%s]", String.join(", ", validValueSupplier));
    }

    static String getIsDateMessage(DateTimeFormatter formatter) {
        return format("must match to date format %s", formatter);
    }

    static String getIsNumericMessage() {
        return VALUE_MUST_BE_NUMERIC;
    }

    static String getContainsNotOnlyMessage(String containedValue) {
        return format("value mustn't contain only %s", containedValue);
    }
}