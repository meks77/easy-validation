package at.meks.validation.validations.date;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;

import java.time.LocalDateTime;
import java.util.function.Supplier;

class CoreDateValidations {

    Validation<LocalDateTime> isDateAfter(Supplier<LocalDateTime> supplierMinDate, Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(date -> supplierMinDate.get().isBefore(date), errorDescription);
    }

}
