package org.meks.validation.fluent;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ValidationPredicate<T> {

    private Predicate<T> predicate;
    private Supplier<T> valueForPredicate;

    public static <X> ValidationPredicate<X> predicate(Supplier<X> predicationValue, Predicate<X> predicate) {
        ValidationPredicate<X> validationPredicate = new ValidationPredicate<>();
        validationPredicate.predicate = predicate;
        validationPredicate.valueForPredicate = predicationValue;
        return validationPredicate;
    }

    Predicate<T> getPredicate() {
        return predicate;
    }

    Supplier<T> getValueForPredicate() {
        return valueForPredicate;
    }
}
