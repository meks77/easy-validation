package org.meks.validation;

import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ValidationResult;

import java.util.function.Predicate;
import java.util.function.Supplier;

class SimpleValidation<T> implements Validation<T> {

    private final Predicate<T> predicate;
    private final Supplier<ErrorDescription> onErrorMessage;

    static <K> SimpleValidation<K> from(Predicate<K> predicate, Supplier<ErrorDescription> onErrorMessage) {
        return new SimpleValidation<>(predicate, onErrorMessage);
    }

    private SimpleValidation(Predicate<T> predicate, Supplier<ErrorDescription> onErrorMessage) {
        this.predicate = predicate;
        this.onErrorMessage = onErrorMessage;
    }

    @Override
    public ValidationResult test(T param) {
        return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage.get());
    }

}
