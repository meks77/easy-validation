package at.meks.validation.validations.date;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

/**
 * This class contains validations for dates.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidationsWithErrorCode {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreDateValidations validations = new CoreDateValidations();

    private DateValidationsWithErrorCode() {
    }

    /**
     * validates if the validated date is after the date provided by the argument minDate.
     * @param minDate   the validated date must be after this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, String errorCode) {
        return isDateAfter(() -> minDate, errorCode);
    }

    /**
     * validates if the validated date is after the date provided by the argument minDateSupplier.
     * @param minDateSupplier   the validated date must be after this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(Supplier<LocalDateTime> minDateSupplier, String errorCode) {
        return validations.isDateAfter(minDateSupplier,
                () -> withCode(messageResolver.getIsDateAfterMessage(minDateSupplier.get()), errorCode));
    }

}
