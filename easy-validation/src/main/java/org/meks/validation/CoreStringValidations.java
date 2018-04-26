package org.meks.validation;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.meks.validation.result.ErrorDescription;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.function.Supplier;

class CoreStringValidations {

    private CoreStringValidations() {}

    static Validation<String> lengthIsMoreThan(int size, ErrorDescription errorDescription){
        return SimpleValidation.from(s -> StringUtils.length(s) > size, () -> errorDescription);
    }

    static Validation<String> lengthIsLessThan(int size, ErrorDescription errorDescription){
        return SimpleValidation.from(s -> StringUtils.length(s) < size, () -> errorDescription);
    }

    static Validation<String> lengthIsBetween(int minSize, int maxSize, ErrorDescription whenMaxSizeExceeds,
                                              ErrorDescription whenMinSizeExceeds){
        return lengthIsMoreThan(minSize - 1, whenMinSizeExceeds).and(lengthIsLessThan(maxSize + 1, whenMaxSizeExceeds));
    }

    static Validation<String> hasLength(int length, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> StringUtils.length(s) == length, () -> errorDescription);
    }

    static Validation<String> contains(String c, ErrorDescription errorDescription){
        return SimpleValidation.from(s -> StringUtils.contains(s, c), () -> errorDescription);
    }

    static Validation<String> isNotBlank(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNotBlank, () -> errorDescription);
    }

    static Validation<String> isInArray(Supplier<String[]> validValueSupplier,
                                        Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(s -> ArrayUtils.contains(validValueSupplier.get(), s), errorDescription);
    }

    static Validation<String> isInList(Supplier<Collection<String>> validValueSupplier,
                                       Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(validValueSupplier.get()::contains, errorDescription);
    }

    static Validation<String> isDate(DateTimeFormatter formatter, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> {
            try {
                formatter.parse(s);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }, () -> errorDescription);
    }

    static Validation<String> isNumeric(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNumeric, () -> errorDescription);
    }

    static Validation<String> containsNotOnly(String containedValue, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> !StringUtils.containsOnly(s, containedValue), () -> errorDescription);
    }
}
