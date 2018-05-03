package at.meks.validation.validations.string;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class StringValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreStringValidations validations = new CoreStringValidations();

    private StringValidationsWithErrorCode() { }

    public static Validation<String> lengthIsMoreThan(int size, String errorCode){
        return validations.lengthIsMoreThan(size, ErrorDescriptionBuilder.withCode(messageResolver.getLengthIsMoreThanMessage(size), errorCode));
    }

    public static Validation<String> lengthIsLessThan(int size, String errorCode){
        return validations.lengthIsLessThan(size, ErrorDescriptionBuilder.withCode(messageResolver.getLengthIsLessThanMessage(size), errorCode));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return validations.lengthIsBetween(minSize, maxSize,
                ErrorDescriptionBuilder.withCode(messageResolver.getLengthIsLessThanMessage(maxSize + 1), maxSizeErrorCode),
                ErrorDescriptionBuilder.withCode(messageResolver.getLengthIsMoreThanMessage(minSize - 1), minSizeErrorCode));
    }

    public static Validation<String> hasLength(int length, String errorCode) {
        return validations.hasLength(length, ErrorDescriptionBuilder.withCode(messageResolver.getHasLenghtMessage(length), errorCode));
    }

    public static Validation<String> contains(String contained, String errorCode){
        return validations.contains(contained, ErrorDescriptionBuilder.withCode(messageResolver.getContainsMessage(contained), errorCode));
    }

    public static Validation<String> isNotBlank(String errorCode) {
        return validations.isNotBlank(ErrorDescriptionBuilder.withCode(messageResolver.getIsNotBlankMessage(), errorCode));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier, String errorCode) {
        return validations.isInArray(validValueSupplier,
                () -> ErrorDescriptionBuilder.withCode(messageResolver.getIsInListMessage(Arrays.asList(validValueSupplier.get())), errorCode));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier, String errorCode) {
        return validations.isInList(validValueSupplier,
                () -> ErrorDescriptionBuilder.withCode(messageResolver.getIsInListMessage(validValueSupplier.get()), errorCode));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return validations.isDate(formatter, ErrorDescriptionBuilder.withCode(messageResolver.getIsDateMessage(formatter), errorCode));
    }

    public static Validation<String> isNumeric(String errorCode) {
        return validations.isNumeric(ErrorDescriptionBuilder.withCode(messageResolver.getIsNumericMessage(), errorCode));
    }

    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return validations.containsNotOnly(containedValue,
                ErrorDescriptionBuilder.withCode(messageResolver.getContainsNotOnlyMessage(containedValue), errorCode));
    }
}