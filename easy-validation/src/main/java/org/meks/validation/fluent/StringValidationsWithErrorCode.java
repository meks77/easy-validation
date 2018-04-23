package org.meks.validation.fluent;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Supplier;

import static java.lang.String.format;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class StringValidationsWithErrorCode {

    private StringValidationsWithErrorCode() {

    }

    public static Validation<String> isNotBlank(String errorCode) {
        return SimpleValidation.from(StringUtils::isNotBlank, withCode("mustn't be blank", errorCode));
    }

    public static Validation<String> isInList(Supplier<String[]> validValueSupplier, String errorCode) {
        return SimpleValidation.from(s -> ArrayUtils.contains(validValueSupplier.get(), s),
                withCode(format("must be in valid list: %s", Arrays.toString(validValueSupplier.get())), errorCode));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return StringValidations.isDate(formatter, withCode(format("must match to date format %s", formatter), errorCode));
    }

    public static Validation<String> isNumeric(String errorCode) {
        return StringValidations.isNumeric(withCode("value must be numeric", errorCode));
    }

    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return StringValidations.containsNotOnly(containedValue, withCode(format("value mustn't contain only %s", containedValue),
                errorCode));
    }
}
