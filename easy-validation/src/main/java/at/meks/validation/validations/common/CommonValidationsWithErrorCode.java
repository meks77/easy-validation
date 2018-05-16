package at.meks.validation.validations.common;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

@SuppressWarnings("WeakerAccess")
public class CommonValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreCommonValidations validations = new CoreCommonValidations();

    private CommonValidationsWithErrorCode() {
    }

    /**
     * returns a validation which validates that a value is not null.
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> notNull(String errorCode) {
        return validations.notNull(ErrorDescriptionBuilder.withCode(messageResolver.getNotNullMessage(), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is equal to anotherone. If both are null it is
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
     * returns a validation which validates that the validated value is equal to anotherone. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isEqualTo(Supplier<T> compareTo, String errorCode) {
        return validations.isEqualTo(compareTo,
                () -> withCode(messageResolver.getIsEqualToMessage(compareTo.get()), errorCode));
    }

    /**
     * returns a validation which validates that the validated value is NOT equal to anotherone. If both are null it is
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
     * returns a validation which validates that the validated value is NOT equal to anotherone. If both are null it is
     * also equal.
     * @param compareTo the validated value is compared to this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @param <T>   type of the tested value
     * @return  new instance of a validation
     */
    public static <T> Validation<T> isNotEqualTo(Supplier<T> compareTo, String errorCode) {
        return validations.isNotEqualTo(compareTo,
                () -> withCode(messageResolver.getIsNotEqualToMessage(compareTo.get()), errorCode));
    }
}
