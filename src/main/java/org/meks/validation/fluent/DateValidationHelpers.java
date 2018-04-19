package org.meks.validation.fluent;

import org.meks.validation.fluent.result.ErrorDescription;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withCode;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withMessage;

public class DateValidationHelpers {

    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, String errorCode) {
        return isDateAfter(minDate, withCode(format("date must be after %s", minDate), errorCode));
    }

    public static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate) {
        return isDateAfter(minDate, withMessage(format("date must be after %s", minDate)));
    }

    private static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, ErrorDescription errorDescription) {
        return SimpleValidation.from(minDate::isBefore, errorDescription);
    }
}
