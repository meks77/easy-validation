package org.meks.validation;

import java.util.Objects;

import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class ObjectValidations {

    private ObjectValidations() {
    }

    public static Validation<String> notNull() {
        return SimpleValidation.from(Objects::nonNull, () -> withMessage("must not be null"));
    }

}
