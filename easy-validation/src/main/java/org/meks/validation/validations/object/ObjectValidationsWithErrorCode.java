package org.meks.validation.validations.object;

import org.meks.validation.ErrorMessageResolver;
import org.meks.validation.Validation;

import static org.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class ObjectValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreObjectValidations validations = new CoreObjectValidations();

    private ObjectValidationsWithErrorCode() {
    }

    public static <T> Validation<T> notNull(String errorCode) {
        return validations.notNull(withCode(messageResolver.getNotNullMessage(), errorCode));
    }

}
