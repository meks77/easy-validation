package at.meks.validation.validations.string;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.function.Supplier;

class CoreStringValidations {

    Validation<String> lengthIsMoreThan(Supplier<Integer> size, Supplier<ErrorDescription> errorDescription){
        return SimpleValidation.from(s -> StringUtils.length(s) > size.get(), errorDescription);
    }

    Validation<String> lengthIsLessThan(Supplier<Integer> size, Supplier<ErrorDescription> errorDescription){
        return SimpleValidation.from(s -> StringUtils.length(s) < size.get(), errorDescription);
    }

    Validation<String> lengthIsBetween(Supplier<Integer> minSize, Supplier<Integer> maxSize,
                                       Supplier<ErrorDescription> errorDescription){
        return lengthIsMoreThan(() -> minSize.get() - 1, errorDescription)
                .and(lengthIsLessThan(() -> maxSize.get() + 1, errorDescription));
    }

    Validation<String> hasLength(Supplier<Integer> length, Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(s -> StringUtils.length(s) == length.get(),errorDescription);
    }

    Validation<String> contains(Supplier<String> c, Supplier<ErrorDescription> errorDescription){
        return SimpleValidation.from(s -> StringUtils.contains(s, c.get()), errorDescription);
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

    Validation<String> isDate(Supplier<DateTimeFormatter> formatter, Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(s -> {
            try {
                formatter.get().parse(s);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }, errorDescription);
    }

    Validation<String> isNumeric(ErrorDescription errorDescription) {
        return SimpleValidation.from(StringUtils::isNumeric, () -> errorDescription);
    }

    Validation<String> containsNotOnly(Supplier<String> containedValue, Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(s -> !StringUtils.containsOnly(s, containedValue.get()), errorDescription);
    }
}
