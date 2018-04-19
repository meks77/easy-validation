package org.meks.validation.fluent;

import org.meks.validation.fluent.result.ErrorDescription;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withCode;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withMessage;

/**
 * This class contains validations for dates.
 */
public class DateValidations {

    private DateValidations() {
    }

    /**
     * validates if the validated date is after the date provided by the argument minDate.
     * @param minDate   the validated date must be after this one
     * @param errorCode in the case the validation violates this code is reported in the result
     * @return  a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, String errorCode) {
        return isDateAfter(minDate, withCode(format("date must be after %s", minDate), errorCode));
    }

    /**
     * validates if the validated date is after the date provided by the argument minDate.
     * @param minDate the validated date must be after this one
     * @return a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate) {
        return isDateAfter(minDate, withMessage(format("date must be after %s", minDate)));
    }

    private static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, ErrorDescription errorDescription) {
        return SimpleValidation.from(minDate::isBefore, errorDescription);
    }
}
