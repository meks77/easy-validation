package at.meks.validation.validations.common;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;

import java.util.Objects;
import java.util.function.Supplier;

class CoreCommonValidations {

    <T> Validation<T> notNull(ErrorDescription errorDescription) {
        return SimpleValidation.from(Objects::nonNull, () -> errorDescription);
    }

    <T> Validation<T> isEqualTo(Supplier<T> compareTo, Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(validated -> Objects.equals(validated, compareTo.get()), errorDescription);
    }

    <T> Validation<T> isNotEqualTo(Supplier<T> compareTo, Supplier<ErrorDescription> errorDescritpion) {
        return SimpleValidation.from(validated -> !Objects.equals(validated, compareTo.get()), errorDescritpion);
    }
}
