package org.meks.validation.fluent;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.meks.validation.fluent.result.ErrorDescription;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

import static java.lang.String.format;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withCode;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class StringValidations {

    private StringValidations() {

    }

    public static Validation<String> notNull() {
        return SimpleValidation.from(Objects::nonNull, withMessage("must not be null."));
    }

    public static Validation<String> lengthIsMoreThan(int size){
        return SimpleValidation.from(s -> s.length() > size, withMessage(format("must have more than %s chars.", size)));
    }

    public static Validation<String> lengthIsLessThan(int size){
        return SimpleValidation.from(s -> s.length() < size, withMessage(format("must have less than %s chars.", size)));
    }

    public static Validation<String> lengthIsBetween(int minSize, int maxSize){
        return lengthIsMoreThan(minSize - 1).and(lengthIsLessThan(maxSize + 1));
    }

    public static Validation<String> hasLength(int length) {
        return SimpleValidation.from(s -> s.length() == length, withMessage(format("length must be %s chars.", length)));
    }

    public static Validation<String> contains(String c){
        return SimpleValidation.from(s -> s.contains(c), withMessage(format("must contain %s", c)));
    }

    public static Validation<String> isNotBlank() {
        return SimpleValidation.from(StringUtils::isNotBlank, withMessage("mustn't be blank"));
    }

    public static Validation<String> isNotBlank(String errorCode) {
        return SimpleValidation.from(StringUtils::isNotBlank, withCode("mustn't be blank", errorCode));
    }

    public static Validation<String> isInList(Supplier<String[]> validValueSupplier) {
        return isInList(validValueSupplier, withMessage(format("must be in valid list: %s", Arrays.toString(validValueSupplier.get()))));
    }

    public static Validation<String> isInList(Supplier<String[]> validValueSupplier, String errorCode) {
        return SimpleValidation.from(s -> ArrayUtils.contains(validValueSupplier.get(), s),
                withCode(format("must be in valid list: %s", Arrays.toString(validValueSupplier.get())), errorCode));
    }

    private static Validation<String> isInList(Supplier<String[]> validValueSupplier, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> ArrayUtils.contains(validValueSupplier.get(), s),
                errorDescription);
    }

    public static Validation<String> isDate(DateTimeFormatter formatter) {
        return isDate(formatter, withMessage(format("must match to date format %s", formatter)));
    }

    public static Validation<String> isDate(DateTimeFormatter formatter, String errorCode) {
        return isDate(formatter, withCode(format("must match to date format %s", formatter), errorCode));
    }

    private static Validation<String> isDate(DateTimeFormatter formatter, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> {
            try {
                formatter.parse(s);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }, errorDescription);
    }

    public static Validation<String> isNumeric(String errorCode) {
        return isNumeric(withCode("value must be numeric", errorCode));
    }

    public static Validation<String> isNumeric() {
        return isNumeric(withMessage("value must be numeric"));
    }

    private static Validation<String> isNumeric(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNumeric, errorDescription);
    }

    public static Validation<String> containsNotOnly(String containedValue, String errorCode) {
        return containsNotOnly(containedValue, withCode(format("value mustn't contain only %s", containedValue),
                errorCode));
    }

    public static Validation<String> containsNotOnly(String containedValue) {
        return containsNotOnly(containedValue, withMessage(format("value mustn't contain only %s", containedValue)));
    }

    private static Validation<String> containsNotOnly(String containedValue, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> !StringUtils.containsOnly(s, containedValue), errorDescription);
    }
}
