package at.meks.validation.validations.date;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.time.LocalDateTime;

/**
 * This class contains validations for dates.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidations {

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();
    private static CoreDateValidations validations = new CoreDateValidations();

    private DateValidations() {
    }

    /**
     * validates if the validated date is after the date provided by the argument minDate.
     * @param minDate the validated date must be after this one
     * @return a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate) {
        return validations.isDateAfter(minDate, ErrorDescriptionBuilder.withMessage(messageResolver.getIsDateAfterMessage(minDate)));
    }

}
