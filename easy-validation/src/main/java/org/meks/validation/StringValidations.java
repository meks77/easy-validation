package org.meks.validation;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ErrorDescriptionBuilder;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static java.lang.String.format;

@SuppressWarnings("WeakerAccess")
public class StringValidations {

    private StringValidations() {

    }

    public static Validation<String> lengthIsMoreThan(int size){
        return SimpleValidation.from(s -> StringUtils.length(s) > size, ErrorDescriptionBuilder.withMessage(format("must have more than %s chars", size)));
    }

    public static Validation<String> lengthIsLessThan(int size){
        return SimpleValidation.from(s -> StringUtils.length(s) < size, ErrorDescriptionBuilder.withMessage(format("must have less than %s chars", size)));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return lengthIsMoreThan(minSize - 1).and(lengthIsLessThan(maxSize + 1));
    }

    public static Validation<String> hasLength(int length) {
        return SimpleValidation.from(s -> StringUtils.length(s) == length, ErrorDescriptionBuilder.withMessage(format("length must be %s chars", length)));
    }

    public static Validation<String> contains(String c){
        return SimpleValidation.from(s -> StringUtils.contains(s, c), ErrorDescriptionBuilder.withMessage(format("must contain %s", c)));
    }

    public static Validation<String> isNotBlank() {
        return SimpleValidation.from(StringUtils::isNotBlank, ErrorDescriptionBuilder.withMessage("mustn't be blank"));
    }

    public static Validation<String> isInArray(Supplier<String[]> validValueSupplier) {
        return isInList(validValueSupplier, ErrorDescriptionBuilder.withMessage(format("must be in list: %s",
                Arrays.toString(validValueSupplier.get()))));
    }

    private static Validation<String> isInList(Supplier<String[]> validValueSupplier, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> ArrayUtils.contains(validValueSupplier.get(), s),
                errorDescription);
    }

    public static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier) {
        return SimpleValidation.from(validValueSupplier.get()::contains,
                ErrorDescriptionBuilder.withMessage(format("must be in list: [%s]", String.join(", ", validValueSupplier.get()))));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return isDate(formatter, ErrorDescriptionBuilder.withMessage(format("must match to date format %s", formatter)));
    }

    static Validation<String> isDate(DateTimeFormatter formatter, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> {
            try {
                formatter.parse(s);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }, errorDescription);
    }

    public static Validation<String> isNumeric() {
        return isNumeric(ErrorDescriptionBuilder.withMessage("value must be numeric"));
    }

    static Validation<String> isNumeric(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNumeric, errorDescription);
    }

    public static Validation<String> containsNotOnly(String containedValue) {
        return containsNotOnly(containedValue, ErrorDescriptionBuilder.withMessage(format("value mustn't contain only %s", containedValue)));
    }

    static Validation<String> containsNotOnly(String containedValue, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> !StringUtils.containsOnly(s, containedValue), errorDescription);
    }
}