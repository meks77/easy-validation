package at.meks.validation.validations.number;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class NumberValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreNumberValidations validations = new CoreNumberValidations();

    private NumberValidations() {

    }

    public static <T extends Number> Validation<T> isLessThan(T compareTo) {
        return validations.isLessThan(compareTo, withMessage(messageResolver.getIsNumberLessThanMessage(compareTo)));
    }

    public static <T extends Number> Validation<T> isGreaterThan(T compareTo) {
        return validations.isGreaterThan(compareTo, withMessage(messageResolver.getIsNumberGreaterThanMessage(compareTo)));
    }

    public static <T extends Number> Validation<T> isBetween(T min, T max) {
        return validations.isBetween(min, max, withMessage(messageResolver.getNumberIsBetweenMessage(min, max)));
    }

    public static <T extends Number> Validation<T> isInt() {
        return validations.isInt(withMessage(messageResolver.getIsIntMessage()));
    }

    public static <T extends Number> Validation<T> isByte() {
        return validations.isByte(withMessage(messageResolver.getIsByteMessage()));
    }

    public static <T extends Number> Validation<T> isShort() {
        return validations.isShort(withMessage(messageResolver.getIsShortMessage()));
    }
}
