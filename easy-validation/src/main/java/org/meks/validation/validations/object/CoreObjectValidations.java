package org.meks.validation.validations.object;

import org.meks.validation.SimpleValidation;
import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;

import java.util.Objects;

class CoreObjectValidations {

    Validation<String> notNull(ErrorDescription errorDescription) {
        return SimpleValidation.from(Objects::nonNull, () -> errorDescription);
    }

}
