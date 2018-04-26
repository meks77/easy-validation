package org.meks.validation;

import static org.meks.validation.ErrorMessageResolver.getNotNullMessage;
import static org.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class ObjectValidationsWithErrorCode {

    private ObjectValidationsWithErrorCode() {
    }

    public static Validation<String> notNull(String errorCode) {
        return CoreObjectValidations.notNull(withCode(getNotNullMessage(), errorCode));
    }

}
