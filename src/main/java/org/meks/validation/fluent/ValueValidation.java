package org.meks.validation.fluent;

import org.meks.validation.fluent.result.ValidationException;
import org.meks.validation.fluent.result.ValidationResult;

import java.util.function.Supplier;

public class ValueValidation<K> {

    private final Validation<K> validation;
    private final Supplier<K> validatedValue;
    private ValidationPredicate<K> predicate;
    private String valueDescrption;

    public ValueValidation(Validation<K> validation, Supplier<K> validatedValue) {
        this.validation = validation;
        this.validatedValue = validatedValue;
    }

    public ValidationResult doValidate() {
        if (predicate.getPredicate().test(predicate.getValueForPredicate().get())) {
            return validation.test(validatedValue);
        }
        return ValidationResult.ok();
    }

    public void doValidateAndThrow() throws ValidationException {
        doValidate().throwIfInvalid(valueDescrption);
    }

    void setValueDescrption(String valueDescrption) {
        this.valueDescrption = valueDescrption;
    }

    void setPredicate(ValidationPredicate<K> predicate) {
        this.predicate = predicate;
    }
}
