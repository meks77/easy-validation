package at.meks.validation.validations.string;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class StringValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreStringValidations validations = new CoreStringValidations();

    private StringValidations() { }

    public static Validation<String> lengthIsMoreThan(int size){
        return validations.lengthIsMoreThan(size, ErrorDescriptionBuilder.withMessage(messageResolver.getLengthIsMoreThanMessage(size)));
    }

    public static Validation<String> lengthIsLessThan(int size){
        return validations.lengthIsLessThan(size, ErrorDescriptionBuilder.withMessage(messageResolver.getLengthIsLessThanMessage(size)));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return validations.lengthIsBetween(minSize, maxSize,
                ErrorDescriptionBuilder.withMessage(messageResolver.getLengthIsLessThanMessage(maxSize + 1)),
                ErrorDescriptionBuilder.withMessage(messageResolver.getLengthIsMoreThanMessage(minSize - 1)));
    }

    public static Validation<String> hasLength(int length) {
        return validations.hasLength(length, ErrorDescriptionBuilder.withMessage(messageResolver.getHasLenghtMessage(length)));
    }

    public static Validation<String> contains(String contained){
        return validations.contains(contained, ErrorDescriptionBuilder.withMessage(messageResolver.getContainsMessage(contained)));
    }

    public static Validation<String> isNotBlank() {
        return validations.isNotBlank(ErrorDescriptionBuilder.withMessage(messageResolver.getIsNotBlankMessage()));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier) {
        return validations.isInArray(validValueSupplier,
                () -> ErrorDescriptionBuilder.withMessage(messageResolver.getIsInListMessage(Arrays.asList(validValueSupplier.get()))));
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier) {
        return validations.isInList(validValueSupplier,
                () -> ErrorDescriptionBuilder.withMessage(messageResolver.getIsInListMessage(validValueSupplier.get())));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return validations.isDate(formatter, ErrorDescriptionBuilder.withMessage(messageResolver.getIsDateMessage(formatter)));
    }

    public static Validation<String> isNumeric() {
        return validations.isNumeric(ErrorDescriptionBuilder.withMessage(messageResolver.getIsNumericMessage()));
    }

    public static Validation<String> containsNotOnly(String containedValue) {
        return validations.containsNotOnly(containedValue,
                ErrorDescriptionBuilder.withMessage(messageResolver.getContainsNotOnlyMessage(containedValue)));
    }
}