package org.meks.validation.list;

import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescription;
import org.meks.validation.result.ValidationResult;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SimpleListValidation<T> implements Validation<List<T>> {

    private final Predicate<List<T>> predicate;
    private final ErrorDescription onErrorMessage;

    private SimpleListValidation(Predicate<List<T>> predicate, ErrorDescription onErrorMessage) {
        this.predicate = predicate;
        this.onErrorMessage = onErrorMessage;
    }


    @SuppressWarnings("WeakerAccess")
    public static <X> SimpleListValidation<X> forList(Predicate<List<X>> predicate, ErrorDescription errorDescription) {
        return new SimpleListValidation<>(predicate, errorDescription);
    }

    @Override
    public ValidationResult test(Supplier<List<T>> list) {
        return predicate.test(list.get()) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage);
    }

}
