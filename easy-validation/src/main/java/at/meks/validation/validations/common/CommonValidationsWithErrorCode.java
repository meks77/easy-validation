package at.meks.validation.validations.common;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;
import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

@SuppressWarnings("WeakerAccess")
public class CommonValidationsWithErrorCode {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();
    private static final CoreCommonValidations VALIDATIONS = new CoreCommonValidations();

    private CommonValidationsWithErrorCode() {
    }

    /**
     * returns a validation which validates that a value is not null.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> notNull(String errorCode) {
        return VALIDATIONS.notNull(ErrorDescriptionBuilder.withCode(MESSAGE_RESOLVER.getNotNullMessage(), errorCode));
    }

    /**
     * returns a validation which validates that a value is null.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNull(String errorCode) {
        return VALIDATIONS.isNull(withCode(MESSAGE_RESOLVER.getIsNullMessage(), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(T compareTo, String errorCode) {
        return isEqualTo(() -> compareTo, errorCode);
    }

    /**
     * returns a validation which validates that the validated value is equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(Supplier<T> compareTo, String errorCode) {
        return VALIDATIONS.isEqualTo(compareTo,
                () -> withCode(MESSAGE_RESOLVER.getIsEqualToMessage(compareTo.get()), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(T compareTo, String errorCode) {
        return isNotEqualTo(() -> compareTo, errorCode);
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to another one. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(Supplier<T> compareTo, String errorCode) {
        return VALIDATIONS.isNotEqualTo(compareTo,
                () -> withCode(MESSAGE_RESOLVER.getIsNotEqualToMessage(compareTo.get()), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is less than another one.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isLessThan(T compareTo, String errorCode) {
        return isLessThan(() -> compareTo, errorCode);
    }

    /**
     * returns a validation which validates that the validated value is less than another one.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isLessThan(Supplier<T> compareTo, String errorCode) {
        return VALIDATIONS.isLessThan(compareTo,
                () -> withCode(MESSAGE_RESOLVER.getIsLessThanMessage(compareTo.get()), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is greater than another one.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isGreaterThan(T compareTo, String errorCode) {
        return isGreaterThan(() -> compareTo, errorCode);
    }

    /**
     * returns a validation which validates that the validated value is greater than another one.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isGreaterThan(Supplier<T> compareTo, String errorCode) {
        return VALIDATIONS.isGreaterThan(compareTo,
                () -> withCode(MESSAGE_RESOLVER.getIsGreaterThanMessage(compareTo.get()), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is between two another values.
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isBetween(T min, T max, String errorCode) {
        return isBetween(() -> min, () -> max, errorCode);
    }

    /**
     * returns a validation which validates that the validated value is between two another values.
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @param <C>   the comparable to T
     * @return  new instance of a validation
     */
    public static <T, C extends Comparable<T>> Validation<C> isBetween(Supplier<T> min, Supplier<T> max, String errorCode) {
        return VALIDATIONS.isBetween(min, max,
                () -> withCode(MESSAGE_RESOLVER.getIsBetweenMessage(min.get(), max.get()), errorCode));
    }
}
