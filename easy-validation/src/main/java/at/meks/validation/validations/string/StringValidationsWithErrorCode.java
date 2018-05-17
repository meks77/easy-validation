package at.meks.validation.validations.string;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class StringValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreStringValidations validations = new CoreStringValidations();

    private StringValidationsWithErrorCode() { }

    public static Validation<String> lengthIsMoreThan(int size, String errorCode){
        return lengthIsMoreThan(() -> size, errorCode);
    }

    public static Validation<String> lengthIsMoreThan(Supplier<Integer> size, String errorCode){
        return validations.lengthIsMoreThan(size,
                () -> withCode(messageResolver.getLengthIsMoreThanMessage(size.get()), errorCode));
    }

    public static Validation<String> lengthIsLessThan(int size, String errorCode){
        return lengthIsLessThan(() -> size, errorCode);
    }

    public static Validation<String> lengthIsLessThan(Supplier<Integer> size, String errorCode){
        return validations.lengthIsLessThan(size,
                () -> withCode(messageResolver.getLengthIsLessThanMessage(size.get()), errorCode));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return lengthIsBetween(() -> minSize, () -> maxSize, minSizeErrorCode, maxSizeErrorCode);
    }

    public static Validation<String> lengthIsBetween(Supplier<Integer> minSize, Supplier<Integer> maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return validations.lengthIsBetween(minSize, maxSize,
                () -> withCode(messageResolver.getLengthIsLessThanMessage(maxSize.get() + 1), maxSizeErrorCode),
                () -> withCode(messageResolver.getLengthIsMoreThanMessage(minSize.get() - 1), minSizeErrorCode));
    }

    public static Validation<String> hasLength(int length, String errorCode) {
        return hasLength(() -> length, errorCode);
    }

    public static Validation<String> hasLength(Supplier<Integer> length, String errorCode) {
        return validations.hasLength(length,
                () -> withCode(messageResolver.getHasLengthMessage(length.get()), errorCode));
    }

    public static Validation<String> contains(String contained, String errorCode){
        return contains(() -> contained, errorCode);
    }

    public static Validation<String> contains(Supplier<String> contained, String errorCode){
        return validations.contains(contained,
                () -> withCode(messageResolver.getContainsMessage(contained.get()), errorCode));
    }

    public static Validation<String> isNotBlank(String errorCode) {
        return validations.isNotBlank(withCode(messageResolver.getIsNotBlankMessage(), errorCode));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier, String errorCode) {
        return validations.isInArray(validValueSupplier,
                () -> withCode(messageResolver.getIsInListMessage(Arrays.asList(validValueSupplier.get())), errorCode));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier, String errorCode) {
        return validations.isInList(validValueSupplier,
                () -> withCode(messageResolver.getIsInListMessage(validValueSupplier.get()), errorCode));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return isDate(() -> formatter, errorCode);
    }

    public static Validation<String> isDate(Supplier<DateTimeFormatter> formatter, String errorCode) {
        return validations.isDate(formatter,
                () -> withCode(messageResolver.getIsDateMessage(formatter.get()), errorCode));
    }

    public static Validation<String> isNumeric(String errorCode) {
        return validations.isNumeric(withCode(messageResolver.getIsNumericMessage(), errorCode));
    }

    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return containsNotOnly(() -> containedValue, errorCode);
    }

    public static Validation<String> containsNotOnly(Supplier<String> containedValue, String errorCode) {
        return validations.containsNotOnly(containedValue,
                () -> withCode(messageResolver.getContainsNotOnlyMessage(containedValue.get()), errorCode));
    }
}