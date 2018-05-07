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

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreStringValidations validations = new CoreStringValidations();

    private StringValidations() { }

    /**
     * create a validation which validates that a string has a length more than the provided size argument.
     * @param size  string length is compared to that value and must be greater
     * @return  new validation instance
     */
    public static Validation<String> lengthIsMoreThan(int size){
        return validations.lengthIsMoreThan(size, withMessage(messageResolver.getLengthIsMoreThanMessage(size)));
    }

    /**
     * creates a validation which validates that a string length is less than the provided size argument.
     * @param size  string length is compared to that value and must be less
     * @return  new validation instance
     */
    public static Validation<String> lengthIsLessThan(int size){
        return validations.lengthIsLessThan(size, withMessage(messageResolver.getLengthIsLessThanMessage(size)));
    }

    /**
     * creates a validation which validates if a string length is between the provided size arguments.
     * @param minSize   the minimum size of the string
     * @param maxSize   the maximum size of the string
     * @return  new validation insstance
     */
    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return validations.lengthIsBetween(minSize, maxSize,
                withMessage(messageResolver.getLengthIsLessThanMessage(maxSize + 1)),
                withMessage(messageResolver.getLengthIsMoreThanMessage(minSize - 1)));
    }

    /**
     * creates a validation which validates if a string length is equal to the provided size length argument.
     * @param length    the expected length of the string
     * @return  new validation instance
     */
    public static Validation<String> hasLength(int length) {
        return validations.hasLength(length, withMessage(messageResolver.getHasLenghtMessage(length)));
    }

    /**
     * creates a validation which validates if a string contains an expected text.
     * @param contained the text which is expected somewhere in the string
     * @return  new validation instance
     */
    public static Validation<String> contains(String contained){
        return validations.contains(contained, withMessage(messageResolver.getContainsMessage(contained)));
    }

    /**
     * creates a validation which validates that a string is not blank(see StringUtils of apache commons)
     * @return  new validation instance
     */
    public static Validation<String> isNotBlank() {
        return validations.isNotBlank(withMessage(messageResolver.getIsNotBlankMessage()));
    }

    /**
     * creates a validation which validates that a string is in a list of valid values.
     * @param validValueSupplier    supplier for the list of valid values
     * @return  new validation instance
     */
    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier) {
        return validations.isInArray(validValueSupplier,
                () -> withMessage(messageResolver.getIsInListMessage(Arrays.asList(validValueSupplier.get()))));
    }

    /**
     * creates a validation which validates that a string is in a list of valid values
     * @param validValueSupplier    supplier for the list of valid values
     * @return  new validation instance
     */
    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier) {
        return validations.isInList(validValueSupplier,
                () -> withMessage(messageResolver.getIsInListMessage(validValueSupplier.get())));
    }

    /**
     * creates a validation which validates that a string can be parsed to a date.
     * @param formatter the formatter used to parse the date
     * @return  new validation instance
     */
    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return validations.isDate(formatter, withMessage(messageResolver.getIsDateMessage(formatter)));
    }

    /**
     * creates a validation which validates if a string is numeric. What means numeric: see apache commons-lang
     * String-Utils.isNumeric
     * @return new validation instance
     */
    public static Validation<String> isNumeric() {
        return validations.isNumeric(withMessage(messageResolver.getIsNumericMessage()));
    }

    /**
     * creates a validation which validates if a string contains not only an expected text.
     * @param containedValue    is used to validate if the string contains only this text
     * @return  new validation instance
     */
    public static Validation<String> containsNotOnly(String containedValue) {
        return validations.containsNotOnly(containedValue,
                withMessage(messageResolver.getContainsNotOnlyMessage(containedValue)));
    }
}