package at.meks.validation.validations.string;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

/**
 * This utility class contains method for creating validations for strings.
 */
@SuppressWarnings("WeakerAccess")
public class StringValidations {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreStringValidations VALIDATIONS = new CoreStringValidations();

    private StringValidations() { }

    /**
     * create a validation which validates that a string has a length more than the provided size argument.
     * @param size  string length is compared to that value and must be greater
     * @return  new validation instance
     */
    public static Validation<String> lengthIsMoreThan(int size){
        return lengthIsMoreThan(() -> size);
    }

    /**
     * create a validation which validates that a string has a length more than the provided size argument.
     * @param size  string length is compared to that value and must be greater
     * @return  new validation instance
     */
    public static Validation<String> lengthIsMoreThan(Supplier<Integer> size){
        return VALIDATIONS.lengthIsMoreThan(size,
                () -> withMessage(MESSAGE_RESOLVER.getLengthIsMoreThanMessage(size.get())));
    }

    /**
     * creates a validation which validates that a string length is less than the provided size argument.
     * @param size  string length is compared to that value and must be less
     * @return  new validation instance
     */
    public static Validation<String> lengthIsLessThan(int size){
        return lengthIsLessThan(() -> size);
    }

    /**
     * creates a validation which validates that a string length is less than the provided size argument.
     * @param size  string length is compared to that value and must be less
     * @return  new validation instance
     */
    public static Validation<String> lengthIsLessThan(Supplier<Integer> size){
        return VALIDATIONS.lengthIsLessThan(size, () -> withMessage(MESSAGE_RESOLVER.getLengthIsLessThanMessage(size.get())));
    }

    /**
     * creates a validation which validates if a string length is between the provided size arguments.
     * @param minSize   the minimum size of the string
     * @param maxSize   the maximum size of the string
     * @return  new validation insstance
     */
    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return lengthIsBetween(() -> minSize, () -> maxSize);
    }

    /**
     * creates a validation which validates if a string length is between the provided size arguments.
     * @param minSize   the minimum size of the string
     * @param maxSize   the maximum size of the string
     * @return  new validation insstance
     */
    public static Validation<String> lengthIsBetween(Supplier<Integer> minSize, Supplier<Integer> maxSize){
        return VALIDATIONS.lengthIsBetween(minSize, maxSize,
                () -> withMessage(MESSAGE_RESOLVER.getLengthIsBetweenMessage(minSize.get(), maxSize.get())));
    }

    /**
     * creates a validation which validates if a string length is equal to the provided size length argument.
     * @param length    the expected length of the string
     * @return  new validation instance
     */
    public static Validation<String> hasLength(int length) {
        return hasLength(() -> length);
    }

    /**
     * creates a validation which validates if a string length is equal to the provided size length argument.
     * @param length    the expected length of the string
     * @return  new validation instance
     */
    public static Validation<String> hasLength(Supplier<Integer> length) {
        return VALIDATIONS.hasLength(length, () -> withMessage(MESSAGE_RESOLVER.getHasLengthMessage(length.get())));
    }

    /**
     * creates a validation which validates if a string contains an expected text.
     * @param contained the text which is expected somewhere in the string
     * @return  new validation instance
     */
    public static Validation<String> contains(String contained){
        return contains(() -> contained);
    }

    /**
     * creates a validation which validates if a string contains an expected text.
     * @param contained the text which is expected somewhere in the string
     * @return  new validation instance
     */
    public static Validation<String> contains(Supplier<String> contained){
        return VALIDATIONS.contains(contained, () -> withMessage(MESSAGE_RESOLVER.getContainsMessage(contained.get())));
    }

    /**
     * creates a validation which validates that a string is not blank(see StringUtils of apache commons)
     * @return  new validation instance
     */
    public static Validation<String> isNotBlank() {
        return VALIDATIONS.isNotBlank(withMessage(MESSAGE_RESOLVER.getIsNotBlankMessage()));
    }

    /**
     * creates a validation which validates that a string is in a list of valid values.
     * @param validValueSupplier    supplier for the list of valid values
     * @return  new validation instance
     */
    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier) {
        return VALIDATIONS.isInArray(validValueSupplier,
                () -> withMessage(MESSAGE_RESOLVER.getIsInListMessage(Arrays.asList(validValueSupplier.get()))));
    }

    /**
     * creates a validation which validates that a string is in a list of valid values
     * @param validValueSupplier    supplier for the list of valid values
     * @return  new validation instance
     */
    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier) {
        return VALIDATIONS.isInList(validValueSupplier,
                () -> withMessage(MESSAGE_RESOLVER.getIsInListMessage(validValueSupplier.get())));
    }

    /**
     * creates a validation which validates that a string can be parsed to a date.
     * @param formatter the formatter used to parse the date
     * @return  new validation instance
     */
    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return isDate(() -> formatter);
    }

    /**
     * creates a validation which validates that a string can be parsed to a date.
     * @param formatter the formatter used to parse the date
     * @return  new validation instance
     */
    public static Validation<String> isDate(Supplier<DateTimeFormatter> formatter) {
        return VALIDATIONS.isDate(formatter, () -> withMessage(MESSAGE_RESOLVER.getIsDateMessage(formatter.get())));
    }

    /**
     * creates a validation which validates if a string is numeric. What means numeric: see apache commons-lang
     * String-Utils.isNumeric
     * @return new validation instance
     */
    public static Validation<String> isNumeric() {
        return VALIDATIONS.isNumeric(withMessage(MESSAGE_RESOLVER.getIsNumericMessage()));
    }

    /**
     * creates a validation which validates if a string contains not only an expected text.
     * @param containedValue    is used to validate if the string contains only this text
     * @return  new validation instance
     */
    public static Validation<String> containsNotOnly(String containedValue) {
        return containsNotOnly(() -> containedValue);
    }

    /**
     * creates a validation which validates if a string contains not only an expected text.
     * @param containedValue    is used to validate if the string contains only this text
     * @return  new validation instance
     */
    public static Validation<String> containsNotOnly(Supplier<String> containedValue) {
        return VALIDATIONS.containsNotOnly(containedValue,
                () -> withMessage(MESSAGE_RESOLVER.getContainsNotOnlyMessage(containedValue.get())));
    }
}