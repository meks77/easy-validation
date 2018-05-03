package at.meks.validation.validations.object;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

@SuppressWarnings("WeakerAccess")
public class ObjectValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreObjectValidations validations = new CoreObjectValidations();

    private ObjectValidations() {
    }

    public static <T> Validation<T> notNull() {
        return validations.notNull(ErrorDescriptionBuilder.withMessage(messageResolver.getNotNullMessage()));
    }

}