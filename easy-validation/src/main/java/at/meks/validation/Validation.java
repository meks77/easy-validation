package at.meks.validation;

import at.meks.validation.result.ValidationResult;

import java.util.function.Function;

@FunctionalInterface
public interface Validation<T> {

    ValidationResult test(T param);

    default Validation<T> and(Validation<T> other) {
        return param -> {
            ValidationResult firstResult = this.test(param);
            return !firstResult.isValid() ? firstResult : other.test(param);
        };
    }

    default <R> Validation<T> and(Function<T, R> mapper, Validation<R> other) {
        return param -> {
            ValidationResult firstResult = this.test(param);
            return !firstResult.isValid() ? firstResult : other.test(mapper.apply(param));
        };
    }

    default Validation<T> or(Validation<T> other) {
        return param -> {
            ValidationResult firstResult = this.test(param);
            return firstResult.isValid() ? firstResult : other.test(param);
        };
    }

}