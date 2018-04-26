package org.meks.validation;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static org.meks.validation.ErrorMessageResolver.getContainsMessage;
import static org.meks.validation.ErrorMessageResolver.getContainsNotOnlyMessage;
import static org.meks.validation.ErrorMessageResolver.getHasLenghtMessage;
import static org.meks.validation.ErrorMessageResolver.getIsDateMessage;
import static org.meks.validation.ErrorMessageResolver.getIsInListMessage;
import static org.meks.validation.ErrorMessageResolver.getIsNotBlankMessage;
import static org.meks.validation.ErrorMessageResolver.getIsNumericMessage;
import static org.meks.validation.ErrorMessageResolver.getLenghtIsLessThanMessage;
import static org.meks.validation.ErrorMessageResolver.getLengthIsMoreThanMessage;
import static org.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class StringValidationsWithErrorCode {

    private StringValidationsWithErrorCode() { }

    public static Validation<String> lengthIsMoreThan(int size, String errorCode){
        return CoreStringValidations.lengthIsMoreThan(size, withCode(getLengthIsMoreThanMessage(size), errorCode));
    }

    public static Validation<String> lengthIsLessThan(int size, String errorCode){
        return CoreStringValidations.lengthIsLessThan(size, withCode(getLenghtIsLessThanMessage(size), errorCode));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return CoreStringValidations.lengthIsBetween(minSize, maxSize,
                withCode(getLenghtIsLessThanMessage(maxSize + 1), maxSizeErrorCode),
                withCode(getLengthIsMoreThanMessage(minSize - 1), minSizeErrorCode));
    }

    public static Validation<String> hasLength(int length, String errorCode) {
        return CoreStringValidations.hasLength(length, withCode(getHasLenghtMessage(length), errorCode));
    }

    public static Validation<String> contains(String contained, String errorCode){
        return CoreStringValidations.contains(contained, withCode(getContainsMessage(contained), errorCode));
    }

    public static Validation<String> isNotBlank(String errorCode) {
        return CoreStringValidations.isNotBlank(withCode(getIsNotBlankMessage(), errorCode));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier, String errorCode) {
        return CoreStringValidations.isInArray(validValueSupplier,
                () -> withCode(getIsInListMessage(Arrays.asList(validValueSupplier.get())), errorCode));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier, String errorCode) {
        return CoreStringValidations.isInList(validValueSupplier,
                () -> withCode(getIsInListMessage(validValueSupplier.get()), errorCode));
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