package org.meks.validation;

import java.time.LocalDateTime;

import static org.meks.validation.ErrorMessageResolver.getIsDateAfterMessage;
import static org.meks.validation.result.ErrorDescriptionBuilder.withCode;

/**
 * This class contains validations for dates.
 */
@SuppressWarnings("WeakerAccess")
public class DateValidationsWithErrorCode {

    private DateValidationsWithErrorCode() {
    }

    /**
     * validates if the validated date is after the date provided by the argument minDate.
     * @param minDate   the validated date must be after this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, String errorCode) {
        return CoreDateValidations.isDateAfter(minDate, withCode(getIsDateAfterMessage(minDate), errorCode));
    }

}
