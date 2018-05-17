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

    /**
     * create a validation which validates that a string has a length more than the provided size argument.
     * @param size  string length is compared to that value and must be greater
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> lengthIsMoreThan(int size, String errorCode){
        return lengthIsMoreThan(() -> size, errorCode);
    }

    /**
     * create a validation which validates that a string has a length more than the provided size argument.
     * @param size  string length is compared to that value and must be greater
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> lengthIsMoreThan(Supplier<Integer> size, String errorCode){
        return VALIDATIONS.lengthIsMoreThan(size,
                () -> withCode(MESSAGE_RESOLVER.getLengthIsMoreThanMessage(size.get()), errorCode));
    }

    /**
     * creates a validation which validates that a string length is less than the provided size argument.
     * @param size  string length is compared to that value and must be less
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> lengthIsLessThan(int size, String errorCode){
        return lengthIsLessThan(() -> size, errorCode);
    }

    /**
     * creates a validation which validates that a string length is less than the provided size argument.
     * @param size  string length is compared to that value and must be less
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> lengthIsLessThan(Supplier<Integer> size, String errorCode){
        return VALIDATIONS.lengthIsLessThan(size,
                () -> withCode(MESSAGE_RESOLVER.getLengthIsLessThanMessage(size.get()), errorCode));
    }

    /**
     * creates a validation which validates if a string length is between the provided size arguments.
     * @param minSize   the minimum size of the string
     * @param maxSize   the maximum size of the string
     * @param minSizeErrorCode in the case the validation violates this code is reported in the result
     * @param maxSizeErrorCode in the case the validation violates this code is reported in the result
     * @return  new validation insstance
     */
    public static Validation<String> lengthIsBetween(int minSize, int maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return lengthIsBetween(() -> minSize, () -> maxSize, minSizeErrorCode, maxSizeErrorCode);
    }

    /**
     * creates a validation which validates if a string length is between the provided size arguments.
     * @param minSize   the minimum size of the string
     * @param maxSize   the maximum size of the string
     * @param minSizeErrorCode in the case the validation violates this code is reported in the result
     * @param maxSizeErrorCode in the case the validation violates this code is reported in the result
     * @return  new validation insstance
     */
    public static Validation<String> lengthIsBetween(Supplier<Integer> minSize, Supplier<Integer> maxSize,
                                                     String minSizeErrorCode, String maxSizeErrorCode){
        return VALIDATIONS.lengthIsBetween(minSize, maxSize,
                () -> withCode(MESSAGE_RESOLVER.getLengthIsLessThanMessage(maxSize.get() + 1), maxSizeErrorCode),
                () -> withCode(MESSAGE_RESOLVER.getLengthIsMoreThanMessage(minSize.get() - 1), minSizeErrorCode));
    }

    /**
     * creates a validation which validates if a string length is equal to the provided size length argument.
     * @param length    the expected length of the string
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> hasLength(int length, String errorCode) {
        return hasLength(() -> length, errorCode);
    }

    /**
     * creates a validation which validates if a string length is equal to the provided size length argument.
     * @param length    the expected length of the string
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> hasLength(Supplier<Integer> length, String errorCode) {
        return VALIDATIONS.hasLength(length,
                () -> withCode(MESSAGE_RESOLVER.getHasLengthMessage(length.get()), errorCode));
    }

    /**
     * creates a validation which validates if a string contains an expected text.
     * @param contained the text which is expected somewhere in the string
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> contains(String contained, String errorCode){
        return contains(() -> contained, errorCode);
    }

    /**
     * creates a validation which validates if a string contains an expected text.
     * @param contained the text which is expected somewhere in the string
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> contains(Supplier<String> contained, String errorCode){
        return VALIDATIONS.contains(contained,
                () -> withCode(MESSAGE_RESOLVER.getContainsMessage(contained.get()), errorCode));
    }

    /**
     * creates a validation which validates that a string is not blank(see StringUtils of apache commons)
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> isNotBlank(String errorCode) {
        return VALIDATIONS.isNotBlank(withCode(MESSAGE_RESOLVER.getIsNotBlankMessage(), errorCode));
    }

    /**
     * creates a validation which validates that a string is in a list of valid values.
     * @param validValueSupplier    supplier for the list of valid values
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier, String errorCode) {
        return VALIDATIONS.isInArray(validValueSupplier,
                () -> withCode(MESSAGE_RESOLVER.getIsInListMessage(Arrays.asList(validValueSupplier.get())), errorCode));
    }

    /**
     * creates a validation which validates that a string is in a list of valid values
     * @param validValueSupplier    supplier for the list of valid values
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier, String errorCode) {
        return VALIDATIONS.isInList(validValueSupplier,
                () -> withCode(MESSAGE_RESOLVER.getIsInListMessage(validValueSupplier.get()), errorCode));
    }

    /**
     * creates a validation which validates that a string can be parsed to a date.
     * @param formatter the formatter used to parse the date
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return isDate(() -> formatter, errorCode);
    }

    /**
     * creates a validation which validates that a string can be parsed to a date.
     * @param formatter the formatter used to parse the date
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> isDate(Supplier<DateTimeFormatter> formatter, String errorCode) {
        return VALIDATIONS.isDate(formatter,
                () -> withCode(MESSAGE_RESOLVER.getIsDateMessage(formatter.get()), errorCode));
    }

    /**
     * creates a validation which validates if a string is numeric. What means numeric: see apache commons-lang
     * String-Utils.isNumeric
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return new validation instance
     */
    public static Validation<String> isNumeric(String errorCode) {
        return VALIDATIONS.isNumeric(withCode(MESSAGE_RESOLVER.getIsNumericMessage(), errorCode));
    }

    /**
     * creates a validation which validates if a string contains not only an expected text.
     * @param containedValue    is used to validate if the string contains only this text
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return containsNotOnly(() -> containedValue, errorCode);
    }

    /**
     * creates a validation which validates if a string contains not only an expected text.
     * @param containedValue    is used to validate if the string contains only this text
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static Validation<String> containsNotOnly(Supplier<String> containedValue, String errorCode) {
        return VALIDATIONS.containsNotOnly(containedValue,
                () -> withCode(MESSAGE_RESOLVER.getContainsNotOnlyMessage(containedValue.get()), errorCode));
    }
}