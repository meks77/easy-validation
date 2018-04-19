package org.meks.validation.fluent;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ValueValidationBuilder<T> {


    private Supplier<T> validatedValue;

    private Predicate<?> conidition;

    public static <X> ValueValidationBuilder<X> forValue(Supplier<X> validatedValue) {
        ValueValidationBuilder<X> builder = new ValueValidationBuilder<>();
        builder.setValue(validatedValue);
        return builder;
    }

    private void setValue(Supplier<T> validatedValue) {
        this.validatedValue = validatedValue;
    }

    public <X> ValueValidationBuilder<T> onlyIf(Predicate<X> condition, Supplier<X> valueForPredicateSupplier) {
        this.conidition = condition;
        return this;
    }

    public ValueValidationBuilderWithOptionalArgs<T> withValidation(Validation<T> validation) {
        ValueValidationBuilderWithOptionalArgs<T> builder = new
                ValueValidationBuilderWithOptionalArgs<>(new ValueValidation<>(validation, validatedValue));
        return builder;
    }

}
