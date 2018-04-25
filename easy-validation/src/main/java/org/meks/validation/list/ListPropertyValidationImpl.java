package org.meks.validation.list;

import org.meks.validation.Validation;
import org.meks.validation.result.ValidationResult;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class ListPropertyValidationImpl<T, E> implements Validation<List<T>> {

    private final Function<T, E> propertyGetter;
    private final Validation<List<E>> validation;

    private ListPropertyValidationImpl(Function<T, E> propertyGetter, Validation<List<E>> validation) {
        this.propertyGetter = propertyGetter;
        this.validation = validation;
    }

    @Override
    public ValidationResult test(Supplier<List<T>> param) {
        return validation.test(() -> param.get().stream().map(propertyGetter).collect(Collectors.toList()));
    }

    static <X, Y> Validation<List<X>> onProperty(Function<X, Y> propertyGetter, Validation<List<Y>> validation) {
        return new ListPropertyValidationImpl<>(propertyGetter, validation);
    }

}
