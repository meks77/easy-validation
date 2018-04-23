package org.meks.validation.fluent;

import java.util.Objects;

import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class ObjectValidations {

    private ObjectValidations() {

    }

    public static Validation<String> notNull() {
        return SimpleValidation.from(Objects::nonNull, withMessage("must not be null."));
    }

}
