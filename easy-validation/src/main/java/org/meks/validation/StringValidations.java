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
import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class StringValidations {

    private StringValidations() { }

    public static Validation<String> lengthIsMoreThan(int size){
        return CoreStringValidations.lengthIsMoreThan(size, withMessage(getLengthIsMoreThanMessage(size)));
    }

    public static Validation<String> lengthIsLessThan(int size){
        return CoreStringValidations.lengthIsLessThan(size, withMessage(getLenghtIsLessThanMessage(size)));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return CoreStringValidations.lengthIsBetween(minSize, maxSize,
                withMessage(getLenghtIsLessThanMessage(maxSize + 1)),
                withMessage(getLengthIsMoreThanMessage(minSize - 1)));
    }

    public static Validation<String> hasLength(int length) {
        return CoreStringValidations.hasLength(length, withMessage(getHasLenghtMessage(length)));
    }

    public static Validation<String> contains(String contained){
        return CoreStringValidations.contains(contained, withMessage(getContainsMessage(contained)));
    }

    public static Validation<String> isNotBlank() {
        return CoreStringValidations.isNotBlank(withMessage(getIsNotBlankMessage()));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier) {
        return CoreStringValidations.isInArray(validValueSupplier,
                () -> withMessage(getIsInListMessage(Arrays.asList(validValueSupplier.get()))));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier) {
        return CoreStringValidations.isInList(validValueSupplier,
                () -> withMessage(getIsInListMessage(validValueSupplier.get())));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return CoreStringValidations.isDate(formatter, withMessage(getIsDateMessage(formatter)));
    }

    public static Validation<String> isNumeric() {
        return CoreStringValidations.isNumeric(withMessage(getIsNumericMessage()));
    }

    public static Validation<String> containsNotOnly(String containedValue) {
        return CoreStringValidations.containsNotOnly(containedValue,
                withMessage(getContainsNotOnlyMessage(containedValue)));
    }
}