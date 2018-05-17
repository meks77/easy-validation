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

    /**
     * provides a validation which validates if a values matches to an integer.
     * @param <T>   the type of Number
     * @return  new validation instance
     */
    public static <T extends Number> Validation<T> isInt() {
        return VALIDATIONS.isInt(withMessage(MESSAGE_RESOLVER.getIsIntMessage()));
    }

    /**
     * provides a validation which validates if a values matches to a byte.
     * @param <T>   the type of Number
     * @return  new validation instance
     */
    public static <T extends Number> Validation<T> isByte() {
        return VALIDATIONS.isByte(withMessage(MESSAGE_RESOLVER.getIsByteMessage()));
    }

    /**
     * provides a validation which validates if a values matches to a short.
     * @param <T>   the type of Number
     * @return  new validation instance
     */
    public static <T extends Number> Validation<T> isShort() {
        return VALIDATIONS.isShort(withMessage(MESSAGE_RESOLVER.getIsShortMessage()));
    }
}
