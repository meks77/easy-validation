package org.meks.validation;

import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ValidationResult;

import java.util.function.Predicate;
import java.util.function.Supplier;

class SimpleValidation<K> implements Validation<K> {

    private final Predicate<K> predicate;
    private final ErrorDescription onErrorMessage;

    static <K> SimpleValidation<K> from(Predicate<K> predicate, ErrorDescription onErrorMessage) {
        return new SimpleValidation<>(predicate, onErrorMessage);
    }

    private SimpleValidation(Predicate<K> predicate, ErrorDescription onErrorMessage) {
        this.predicate = predicate;
        this.onErrorMessage = onErrorMessage;
    }

    @Override
    public ValidationResult test(Supplier<K> param) {
        return predicate.test(param.get()) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage);
    }

}
