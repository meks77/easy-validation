package at.meks.validation.validations.number;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class NumberValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreNumberValidations validations = new CoreNumberValidations();

    private NumberValidations() {

    }

    public static <T extends Number> Validation<T> isLessThan(T compareTo) {
        return isLessThan((Supplier<T>) () -> compareTo);
    }

    public static <T extends Number> Validation<T> isLessThan(Supplier<T> compareTo) {
        return validations.isLessThan(compareTo,
                () -> withMessage(messageResolver.getIsNumberLessThanMessage(compareTo.get())));
    }

    public static <T extends Number> Validation<T> isGreaterThan(T compareTo) {
        return isGreaterThan((Supplier<T>) () -> compareTo);
    }

    public static <T extends Number> Validation<T> isGreaterThan(Supplier<T> compareTo) {
        return validations.isGreaterThan(compareTo,
                () -> withMessage(messageResolver.getIsNumberGreaterThanMessage(compareTo.get())));
    }

    public static <T extends Number> Validation<T> isBetween(T min, T max) {
        return isBetween(() -> min, (Supplier<T>) () -> max);
    }

    public static <T extends Number> Validation<T> isBetween(Supplier<T> min, Supplier<T> max) {
        return validations.isBetween(min, max,
                () -> withMessage(messageResolver.getNumberIsBetweenMessage(min.get(), max.get())));
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
