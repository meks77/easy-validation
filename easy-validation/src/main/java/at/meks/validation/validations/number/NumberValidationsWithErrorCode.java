package at.meks.validation.validations.number;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;
import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class NumberValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreNumberValidations validations = new CoreNumberValidations();

    private NumberValidationsWithErrorCode() {

    }

    public static <T extends Number> Validation<T> isLessThan(T compareTo, String errorCode) {
        return isLessThan((Supplier<T>) () -> compareTo, errorCode);
    }

    public static <T extends Number> Validation<T> isLessThan(Supplier<T> compareTo, String errorCode) {
        return validations.isLessThan(compareTo,
                () -> withCode(messageResolver.getIsNumberLessThanMessage(compareTo.get()), errorCode));
    }

    public static <T extends Number> Validation<T> isGreaterThan(T compareTo, String errorCode) {
        return isGreaterThan((Supplier<T>) () -> compareTo, errorCode);
    }

    public static <T extends Number> Validation<T> isGreaterThan(Supplier<T> compareTo, String errorCode) {
        return validations.isGreaterThan(compareTo,
                () -> withCode(messageResolver.getIsNumberGreaterThanMessage(compareTo.get()), errorCode));
    }

    public static <T extends Number> Validation<T> isBetween(T min, T max, String errorCode) {
        return isBetween(() -> min, (Supplier<T>) () -> max, errorCode);
    }

    public static <T extends Number> Validation<T> isBetween(Supplier<T> min, Supplier<T> max, String errorCode) {
        return validations.isBetween(min, max,
                () -> withCode(messageResolver.getNumberIsBetweenMessage(min.get(), max.get()), errorCode));
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
