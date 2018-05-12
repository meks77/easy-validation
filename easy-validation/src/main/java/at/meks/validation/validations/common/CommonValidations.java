package at.meks.validation.validations.common;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

@SuppressWarnings("WeakerAccess")
public class CommonValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreCommonValidations validations = new CoreCommonValidations();

    private CommonValidations() {
    }

    /**
     * returns a validation which validates that a value is not null.
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> notNull() {
        return validations.notNull(ErrorDescriptionBuilder.withMessage(messageResolver.getNotNullMessage()));
    }

}
