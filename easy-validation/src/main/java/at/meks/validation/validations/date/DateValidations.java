package at.meks.validation.validations.date;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

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
        return isDateAfter(() -> minDate);
    }

    /**
     * validates if the validated date is after the date provided by the argument minDateSupplier.
     * @param minDateSupplier the validated date must be after this one
     * @return a new validation instance
     */
    public static Validation<LocalDateTime> isDateAfter(Supplier<LocalDateTime> minDateSupplier) {
        return validations.isDateAfter(minDateSupplier,
                () -> withMessage(messageResolver.getIsDateAfterMessage(minDateSupplier.get())));
    }

}
