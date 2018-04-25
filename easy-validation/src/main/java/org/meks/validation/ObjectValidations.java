package org.meks.validation;

import org.meks.validation.result.ErrorDescriptionBuilder;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class ObjectValidations {

    private ObjectValidations() {
    }

    public static Validation<String> notNull() {
        return SimpleValidation.from(Objects::nonNull, ErrorDescriptionBuilder.withMessage("must not be null"));
    }

}
