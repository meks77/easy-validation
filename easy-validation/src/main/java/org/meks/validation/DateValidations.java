package org.meks.validation;

import java.time.LocalDateTime;

import static org.meks.validation.ErrorMessageResolver.getIsDateAfterMessage;
import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

/**
 * This class contains validations for dates.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidations {

    private DateValidations() {
    }

    /**
     * validates if the validated date is after the date provided by the argument minDate.
     * @param minDate the validated date must be after this one
     * @return a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate) {
        return CoreDateValidations.isDateAfter(minDate, withMessage(getIsDateAfterMessage(minDate)));
    }

}
