package at.meks.validation.validations.object;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

@SuppressWarnings("WeakerAccess")
public class ObjectValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreObjectValidations validations = new CoreObjectValidations();

    private ObjectValidationsWithErrorCode() {
    }

    public static <T> Validation<T> notNull(String errorCode) {
        return validations.notNull(ErrorDescriptionBuilder.withCode(messageResolver.getNotNullMessage(), errorCode));
    }

}
