package at.meks.validation.validations.number;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import org.apache.commons.lang3.Range;

import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class CoreNumberValidations {

    public <T extends Number> Validation<T> isLessThan(Supplier<T> compareTo,
                                                       Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(number -> number.doubleValue() < compareTo.get().doubleValue(), errorDescription);
    }

    public <T extends Number> Validation<T> isGreaterThan(Supplier<T> compareTo,
                                                          Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(number -> number.doubleValue() > compareTo.get().doubleValue(), errorDescription);
    }

    public <T extends Number> Validation<T> isBetween(Supplier<T> min, Supplier<T> max, Supplier<ErrorDescription> errorDescription) {
        return SimpleValidation.from(
                number -> Range.between(min.get().doubleValue(), max.get().doubleValue()).contains(number.doubleValue()),
                errorDescription);
    }

    public <T extends Number> Validation<T> isInt(ErrorDescription errorDescription) {
        return SimpleValidation.from(t -> Range.between((double)Integer.MIN_VALUE, (double) Integer.MAX_VALUE)
                .contains(t.doubleValue()), () -> errorDescription);
    }

    public <T extends Number> Validation<T> isByte(ErrorDescription errorDescription) {
        return SimpleValidation.from(t -> Range.between((double)Byte.MIN_VALUE, (double) Byte.MAX_VALUE)
                .contains(t.doubleValue()), () -> errorDescription);
    }

    public <T extends Number> Validation<T> isShort(ErrorDescription errorDescription) {
        return SimpleValidation.from(t -> Range.between((double)Short.MIN_VALUE, (double) Short.MAX_VALUE)
                .contains(t.doubleValue()), () -> errorDescription);
    }
}
