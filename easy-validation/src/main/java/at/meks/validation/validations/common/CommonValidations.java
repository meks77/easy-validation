package at.meks.validation.validations.common;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

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
        return validations.notNull(withMessage(messageResolver.getNotNullMessage()));
    }

    /**
     * returns a validation which validates that the validated value is equal to anotherone. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(T compareTo) {
        return isEqualTo(() -> compareTo);
    }

    /**
     * returns a validation which validates that the validated value is equal to anotherone. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(Supplier<T> compareTo) {
        return validations.isEqualTo(compareTo, () -> withMessage(messageResolver.getIsEqualToMessage(compareTo.get())));
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to anotherone. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(T compareTo) {
        return isNotEqualTo(() -> compareTo);
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to anotherone. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(Supplier<T> compareTo) {
        return validations.isNotEqualTo(compareTo,
                () -> withMessage(messageResolver.getIsNotEqualToMessage(compareTo.get())));
    }

    public static <T, C extends Comparable<T>> Validation<C> isLessThan(T compareTo) {
        return isLessThan(() -> compareTo);
    }

    public static <T, C extends Comparable<T>> Validation<C> isLessThan(Supplier<T> compareTo) {
        return validations.isLessThan(compareTo,
                () -> withMessage(messageResolver.getIsLessThanMessage(compareTo.get())));
    }

    public static <T, C extends Comparable<T>> Validation<C> isGreaterThan(T compareTo) {
        return isGreaterThan((() -> compareTo));
    }

    public static <T, C extends Comparable<T>> Validation<C> isGreaterThan(Supplier<T> compareTo) {
        return validations.isGreaterThan(compareTo,
                () -> withMessage(messageResolver.getIsGreaterThanMessage(compareTo.get())));
    }

    public static <T, C extends Comparable<T>> Validation<C> isBetween(T min, T max) {
        return isBetween(() -> min, () -> max);
    }

    public static <T, C extends Comparable<T>> Validation<C> isBetween(Supplier<T> min, Supplier<T> max) {
        return validations.isBetween(min, max,
                () -> withMessage(messageResolver.getIsBetweenMessage(min.get(), max.get())));
    }
}
