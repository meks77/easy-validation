package at.meks.validation.validations.number;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class NumberValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreNumberValidations validations = new CoreNumberValidations();

    private NumberValidationsWithErrorCode() {

    }

    public static <T extends Number> Validation<T> isInt(String errorCode) {
        return validations.isInt(withCode(messageResolver.getIsIntMessage(), errorCode));
    }

    public static <T extends Number> Validation<T> isByte(String errorCode) {
        return validations.isByte(withCode(messageResolver.getIsByteMessage(), errorCode));
    }

    public static <T extends Number> Validation<T> isShort(String errorCode) {
        return validations.isShort(withCode(messageResolver.getIsShortMessage(), errorCode));
    }
}
