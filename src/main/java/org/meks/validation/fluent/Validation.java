package org.meks.validation.fluent;

import org.meks.validation.fluent.result.ValidationResult;

import java.util.function.Supplier;

@FunctionalInterface
public interface Validation<K> {

    ValidationResult test(Supplier<K> param);

    default Validation<K> and(Validation<K> other) {
        return (param) -> {
            ValidationResult firstResult = this.test(param);
            return !firstResult.isValid() ? firstResult : other.test(param);
        };
    }

    default Validation<K> or(Validation<K> other) {
        return (param) -> {
            ValidationResult firstResult = this.test(param);
            return firstResult.isValid() ? firstResult : other.test(param);
        };
    }

}