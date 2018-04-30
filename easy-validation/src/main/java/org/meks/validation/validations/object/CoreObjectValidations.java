package org.meks.validation.validations.object;

import org.meks.validation.SimpleValidation;
import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;

import java.util.Objects;

class CoreObjectValidations {

    <T> Validation<T> notNull(ErrorDescription errorDescription) {
        return SimpleValidation.from(Objects::nonNull, () -> errorDescription);
    }

}
