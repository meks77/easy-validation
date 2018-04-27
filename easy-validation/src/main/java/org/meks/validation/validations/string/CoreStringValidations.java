package org.meks.validation.validations.string;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.meks.validation.SimpleValidation;
import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.function.Supplier;

class CoreStringValidations {

    Validation<String> lengthIsMoreThan(int size, ErrorDescription errorDescription){
        return SimpleValidation.from(s -> StringUtils.length(s) > size, () -> errorDescription);
    }

    Validation<String> lengthIsLessThan(int size, ErrorDescription errorDescription){
        return SimpleValidation.from(s -> StringUtils.length(s) < size, () -> errorDescription);
    }

    Validation<String> lengthIsBetween(int minSize, int maxSize, ErrorDescription whenMaxSizeExceeds,
                                              ErrorDescription whenMinSizeExceeds){
        return lengthIsMoreThan(minSize - 1, whenMinSizeExceeds).and(lengthIsLessThan(maxSize + 1, whenMaxSizeExceeds));
    }

    Validation<String> hasLength(int length, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> StringUtils.length(s) == length, () -> errorDescription);
    }

    Validation<String> contains(String c, ErrorDescription errorDescription){
        return SimpleValidation.from(s -> StringUtils.contains(s, c), () -> errorDescription);
    }

    Validation<String> isNotBlank(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNotBlank, () -> errorDescription);
    }

    Validation<String> isInArray(Supplier<String[]> validValueSupplier,
                                        Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(s -> ArrayUtils.contains(validValueSupplier.get(), s), errorDescription);
    }

    Validation<String> isInList(Supplier<Collection<String>> validValueSupplier,
                                       Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(validValueSupplier.get()::contains, errorDescription);
    }

    Validation<String> isDate(DateTimeFormatter formatter, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> {
            try {
                formatter.parse(s);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }, () -> errorDescription);
    }

    Validation<String> isNumeric(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNumeric, () -> errorDescription);
    }

    Validation<String> containsNotOnly(String containedValue, ErrorDescription errorDescription) {
        return SimpleValidation.from(s -> !StringUtils.containsOnly(s, containedValue), () -> errorDescription);
    }
}
