package org.meks.validation.validations.string;

import org.meks.validation.ErrorMessageResolver;
import org.meks.validation.Validation;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class StringValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreStringValidations validations = new CoreStringValidations();

    private StringValidations() { }

    public static Validation<String> lengthIsMoreThan(int size){
        return validations.lengthIsMoreThan(size, withMessage(messageResolver.getLengthIsMoreThanMessage(size)));
    }

    public static Validation<String> lengthIsLessThan(int size){
        return validations.lengthIsLessThan(size, withMessage(messageResolver.getLengthIsLessThanMessage(size)));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return validations.lengthIsBetween(minSize, maxSize,
                withMessage(messageResolver.getLengthIsLessThanMessage(maxSize + 1)),
                withMessage(messageResolver.getLengthIsMoreThanMessage(minSize - 1)));
    }

    public static Validation<String> hasLength(int length) {
        return validations.hasLength(length, withMessage(messageResolver.getHasLenghtMessage(length)));
    }

    public static Validation<String> contains(String contained){
        return validations.contains(contained, withMessage(messageResolver.getContainsMessage(contained)));
    }

    public static Validation<String> isNotBlank() {
        return validations.isNotBlank(withMessage(messageResolver.getIsNotBlankMessage()));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier) {
        return validations.isInArray(validValueSupplier,
                () -> withMessage(messageResolver.getIsInListMessage(Arrays.asList(validValueSupplier.get()))));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier) {
        return validations.isInList(validValueSupplier,
                () -> withMessage(messageResolver.getIsInListMessage(validValueSupplier.get())));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return validations.isDate(formatter, withMessage(messageResolver.getIsDateMessage(formatter)));
    }

    public static Validation<String> isNumeric() {
        return validations.isNumeric(withMessage(messageResolver.getIsNumericMessage()));
    }

    public static Validation<String> containsNotOnly(String containedValue) {
        return validations.containsNotOnly(containedValue,
                withMessage(messageResolver.getContainsNotOnlyMessage(containedValue)));
    }
}