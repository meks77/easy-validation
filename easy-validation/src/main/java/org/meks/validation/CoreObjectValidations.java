package org.meks.validation;

import org.meks.validation.result.ErrorDescription;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class CoreObjectValidations {

    private CoreObjectValidations() {
    }

    public static Validation<String> notNull(ErrorDescription errorDescription) {
        return SimpleValidation.from(Objects::nonNull, () -> errorDescription);
    }

}
