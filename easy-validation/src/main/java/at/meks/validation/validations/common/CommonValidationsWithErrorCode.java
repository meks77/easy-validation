package at.meks.validation.validations.common;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

@SuppressWarnings("WeakerAccess")
public class CommonValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreCommonValidations validations = new CoreCommonValidations();

    private CommonValidationsWithErrorCode() {
    }

    public static <T> Validation<T> notNull(String errorCode) {
        return validations.notNull(ErrorDescriptionBuilder.withCode(messageResolver.getNotNullMessage(), errorCode));
    }

}
