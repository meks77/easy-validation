package org.meks.validation;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Supplier;

import static org.meks.validation.ErrorMessageResolver.getContainsNotOnlyMessage;
import static org.meks.validation.ErrorMessageResolver.getIsDateMessage;
import static org.meks.validation.ErrorMessageResolver.getIsInListMessage;
import static org.meks.validation.ErrorMessageResolver.getIsNumericMessage;
import static org.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class StringValidationsWithErrorCode {

    private StringValidationsWithErrorCode() {

    }

    public static Validation<String> isNotBlank(String errorCode) {
        return CoreStringValidations.isNotBlank(withCode(ErrorMessageResolver.getIsNotBlankMessage(), errorCode));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier, String errorCode) {
        return CoreStringValidations.isInArray(validValueSupplier,
                () -> withCode(getIsInListMessage(Arrays.asList(validValueSupplier.get())), errorCode));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return CoreStringValidations.isDate(formatter, withCode(getIsDateMessage(formatter), errorCode));
    }

    public static Validation<String> isNumeric(String errorCode) {
        return CoreStringValidations.isNumeric(withCode(getIsNumericMessage(), errorCode));
    }

    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return CoreStringValidations.containsNotOnly(containedValue,
                withCode(getContainsNotOnlyMessage(containedValue), errorCode));
    }
}
