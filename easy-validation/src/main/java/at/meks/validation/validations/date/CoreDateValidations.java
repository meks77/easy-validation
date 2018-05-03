package at.meks.validation.validations.date;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;

import java.time.LocalDateTime;

class CoreDateValidations {

    Validation<LocalDateTime> isDateAfter(LocalDateTime minDate, ErrorDescription errorDescription) {
        return SimpleValidation.from(minDate::isBefore, () -> errorDescription);
    }
}
