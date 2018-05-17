package at.meks.validation.validations.number;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class NumberValidationsWithErrorCode {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreNumberValidations VALIDATIONS = new CoreNumberValidations();

    private NumberValidationsWithErrorCode() {

    }

    /**
     * provides a validation which validates if a values matches to an integer.
     * @param <T>   the type of Number
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static <T extends Number> Validation<T> isInt(String errorCode) {
        return VALIDATIONS.isInt(withCode(MESSAGE_RESOLVER.getIsIntMessage(), errorCode));
    }

    /**
     * provides a validation which validates if a values matches to a byte.
     * @param <T>   the type of Number
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static <T extends Number> Validation<T> isByte(String errorCode) {
        return VALIDATIONS.isByte(withCode(MESSAGE_RESOLVER.getIsByteMessage(), errorCode));
    }

    /**
     * provides a validation which validates if a values matches to a short.
     * @param <T>   the type of Number
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  new validation instance
     */
    public static <T extends Number> Validation<T> isShort(String errorCode) {
        return VALIDATIONS.isShort(withCode(MESSAGE_RESOLVER.getIsShortMessage(), errorCode));
    }
}
