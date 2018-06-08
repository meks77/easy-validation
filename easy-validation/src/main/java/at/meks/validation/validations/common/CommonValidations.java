package at.meks.validation.validations.common;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class CommonValidations {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreCommonValidations VALIDATIONS = new CoreCommonValidations();

    private CommonValidations() {
    }

    /**
     * returns a validation which validates that a value is not null.
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> notNull() {
        return VALIDATIONS.notNull(withMessage(MESSAGE_RESOLVER.getNotNullMessage()));
    }

    /**
     * returns a validation which validates that a value is null.
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNull() {
        return VALIDATIONS.isNull(withMessage(MESSAGE_RESOLVER.getIsNullMessage()));
    }

    /**
     * returns a validation which validates that the validated value is equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(T compareTo) {
        return isEqualTo(() -> compareTo);
    }

    /**
     * returns a validation which validates that the validated value is equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(Supplier<T> compareTo) {
        return VALIDATIONS.isEqualTo(compareTo, () -> withMessage(MESSAGE_RESOLVER.getIsEqualToMessage(compareTo.get())));
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(T compareTo) {
        return isNotEqualTo(() -> compareTo);
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(Supplier<T> compareTo) {
        return VALIDATIONS.isNotEqualTo(compareTo,
                () -> withMessage(MESSAGE_RESOLVER.getIsNotEqualToMessage(compareTo.get())));
    }

    /**
     * returns a validation which validates that the validated value is less than another one.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isLessThan(T compareTo) {
        return isLessThan(() -> compareTo);
    }

    /**
     * returns a validation which validates that the validated value is less than another one.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isLessThan(Supplier<T> compareTo) {
        return VALIDATIONS.isLessThan(compareTo,
                () -> withMessage(MESSAGE_RESOLVER.getIsLessThanMessage(compareTo.get())));
    }

    /**
     * returns a validation which validates that the validated value is greater than another one.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isGreaterThan(T compareTo) {
        return isGreaterThan((() -> compareTo));
    }

    /**
     * returns a validation which validates that the validated value is greater than another one.
     * @param compareTo the validated value is compared to this one
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isGreaterThan(Supplier<T> compareTo) {
        return VALIDATIONS.isGreaterThan(compareTo,
                () -> withMessage(MESSAGE_RESOLVER.getIsGreaterThanMessage(compareTo.get())));
    }

    /**
     * returns a validation which validates that the validated value is between two another values.
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isBetween(T min, T max) {
        return isBetween(() -> min, () -> max);
    }

    /**
     * returns a validation which validates that the validated value is between two another values.
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isBetween(Supplier<T> min, Supplier<T> max) {
        return VALIDATIONS.isBetween(min, max,
                () -> withMessage(MESSAGE_RESOLVER.getIsBetweenMessage(min.get(), max.get())));
    }

    /**
     * returns a validation which validats that the validated value is true.
     * @return  new instance of a validation
     */
    public static Validation<Boolean> isTrue() {
        return VALIDATIONS.isTrue(() -> withMessage(MESSAGE_RESOLVER.getIsTrueMessage()));
    }

    /**
     * returns a validation which validats that the validated value is false.
     * @return  new instance of a validation
     */
    public static Validation<Boolean> isFalse() {
        return VALIDATIONS.isFalse(() -> withMessage(MESSAGE_RESOLVER.getIsFalseMessage()));
    }
}
