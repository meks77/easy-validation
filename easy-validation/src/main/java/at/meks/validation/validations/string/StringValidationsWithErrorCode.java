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

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreStringValidations VALIDATIONS = new CoreStringValidations();

    private StringValidationsWithErrorCode() { }

    public static Validation<String> lengthIsMoreThan(int size, String errorCode){
        return lengthIsMoreThan(() -> size, errorCode);
    }

    public static Validation<String> lengthIsMoreThan(Supplier<Integer> size, String errorCode){
        return VALIDATIONS.lengthIsMoreThan(size,
                () -> withCode(MESSAGE_RESOLVER.getLengthIsMoreThanMessage(size.get()), errorCode));
    }

    public static Validation<String> lengthIsLessThan(int size, String errorCode){
        return lengthIsLessThan(() -> size, errorCode);
    }

    public static Validation<String> lengthIsLessThan(Supplier<Integer> size, String errorCode){
        return VALIDATIONS.lengthIsLessThan(size,
                () -> withCode(MESSAGE_RESOLVER.getLengthIsLessThanMessage(size.get()), errorCode));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return lengthIsBetween(() -> minSize, () -> maxSize, minSizeErrorCode, maxSizeErrorCode);
    }

    public static Validation<String> lengthIsBetween(Supplier<Integer> minSize, Supplier<Integer> maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return VALIDATIONS.lengthIsBetween(minSize, maxSize,
                () -> withCode(MESSAGE_RESOLVER.getLengthIsLessThanMessage(maxSize.get() + 1), maxSizeErrorCode),
                () -> withCode(MESSAGE_RESOLVER.getLengthIsMoreThanMessage(minSize.get() - 1), minSizeErrorCode));
    }

    public static Validation<String> hasLength(int length, String errorCode) {
        return hasLength(() -> length, errorCode);
    }

    public static Validation<String> hasLength(Supplier<Integer> length, String errorCode) {
        return VALIDATIONS.hasLength(length,
                () -> withCode(MESSAGE_RESOLVER.getHasLengthMessage(length.get()), errorCode));
    }

    public static Validation<String> contains(String contained, String errorCode){
        return contains(() -> contained, errorCode);
    }

    public static Validation<String> contains(Supplier<String> contained, String errorCode){
        return VALIDATIONS.contains(contained,
                () -> withCode(MESSAGE_RESOLVER.getContainsMessage(contained.get()), errorCode));
    }

    public static Validation<String> isNotBlank(String errorCode) {
        return VALIDATIONS.isNotBlank(withCode(MESSAGE_RESOLVER.getIsNotBlankMessage(), errorCode));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier, String errorCode) {
        return VALIDATIONS.isInArray(validValueSupplier,
                () -> withCode(MESSAGE_RESOLVER.getIsInListMessage(Arrays.asList(validValueSupplier.get())), errorCode));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier, String errorCode) {
        return VALIDATIONS.isInList(validValueSupplier,
                () -> withCode(MESSAGE_RESOLVER.getIsInListMessage(validValueSupplier.get()), errorCode));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return isDate(() -> formatter, errorCode);
    }

    public static Validation<String> isDate(Supplier<DateTimeFormatter> formatter, String errorCode) {
        return VALIDATIONS.isDate(formatter,
                () -> withCode(MESSAGE_RESOLVER.getIsDateMessage(formatter.get()), errorCode));
    }

    public static Validation<String> isNumeric(String errorCode) {
        return VALIDATIONS.isNumeric(withCode(MESSAGE_RESOLVER.getIsNumericMessage(), errorCode));
    }

    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return containsNotOnly(() -> containedValue, errorCode);
    }

    public static Validation<String> containsNotOnly(Supplier<String> containedValue, String errorCode) {
        return VALIDATIONS.containsNotOnly(containedValue,
                () -> withCode(MESSAGE_RESOLVER.getContainsNotOnlyMessage(containedValue.get()), errorCode));
    }
}