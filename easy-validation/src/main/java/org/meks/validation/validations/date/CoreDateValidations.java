package org.meks.validation.validations.date;

import org.meks.validation.SimpleValidation;
import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;

import java.time.LocalDateTime;

class CoreDateValidations {

    Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, ErrorDescription errorDescription) {
        return SimpleValidation.from(minDate::isBefore, () -> errorDescription);
    }
}
