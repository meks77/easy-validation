package at.meks.validation.validations.number;

import at.meks.validation.SimpleValidation;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import org.apache.commons.lang3.Range;

@SuppressWarnings("WeakerAccess")
public class CoreNumberValidations {

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
