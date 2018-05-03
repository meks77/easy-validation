package at.meks.validation.validations.object;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;

import java.util.Objects;

class CoreObjectValidations {

    <T> Validation<T> notNull(ErrorDescription errorDescription) {
        return SimpleValidation.from(Objects::nonNull, () -> errorDescription);
    }

}
