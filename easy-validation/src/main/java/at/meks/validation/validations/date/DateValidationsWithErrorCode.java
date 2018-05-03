package at.meks.validation.validations.date;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.time.LocalDateTime;

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
        return validations.isDateAfter(minDate,
                ErrorDescriptionBuilder.withCode(messageResolver.getIsDateAfterMessage(minDate), errorCode));
    }

}
