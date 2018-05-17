package at.meks.validation.validations.number;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class NumberValidations {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreNumberValidations VALIDATIONS = new CoreNumberValidations();

    private NumberValidations() {

    }

    public static <T extends Number> Validation<T> isInt() {
        return VALIDATIONS.isInt(withMessage(MESSAGE_RESOLVER.getIsIntMessage()));
    }

    public static <T extends Number> Validation<T> isByte() {
        return VALIDATIONS.isByte(withMessage(MESSAGE_RESOLVER.getIsByteMessage()));
    }

    public static <T extends Number> Validation<T> isShort() {
        return VALIDATIONS.isShort(withMessage(MESSAGE_RESOLVER.getIsShortMessage()));
    }
}
