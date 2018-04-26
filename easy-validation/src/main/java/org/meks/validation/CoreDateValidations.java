package org.meks.validation;

import org.meks.validation.result.ErrorDescription;

import java.time.LocalDateTime;

class CoreDateValidations {

    private CoreDateValidations() {
    }

    static Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, ErrorDescription errorDescription) {
        return SimpleValidation.from(minDate::isBefore, () -> errorDescription);
    }
}
