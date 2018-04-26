package org.meks.validation;

import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class ObjectValidations {

    private ObjectValidations() {
    }

    public static Validation<String> notNull() {
        return CoreObjectValidations.notNull(withMessage(ErrorMessageResolver.getNotNullMessage()));
    }

}
