package org.meks.validation.validations.object;

import org.meks.validation.ErrorMessageResolver;
import org.meks.validation.Validation;

import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class ObjectValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreObjectValidations validations = new CoreObjectValidations();

    private ObjectValidations() {
    }

    public static Validation<String> notNull() {
        return validations.notNull(withMessage(messageResolver.getNotNullMessage()));
    }

}
