package org.meks.validation.fluent.list;

import org.meks.validation.fluent.Validation;

import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;
import static org.meks.validation.fluent.result.ErrorDescriptionBuilder.withMessage;

public class ListValidations {

    public static <T> Validation<List<T>> containsOnly(T containedValue) {
        return SimpleListValidation.forList(list -> list.stream().allMatch(t -> t.equals(containedValue)),
                withMessage(format("list must contain only %s", containedValue)));
    }

    public static <T> Validation<List<T>> contains(T containedValue) {
        return SimpleListValidation.forList(list -> list.stream().anyMatch(t -> t.equals(containedValue)),
                withMessage(format("list must contain %s", containedValue)));
    }

    public static <T> Validation<List<T>> doesNotContain(T excludedValue) {
        return SimpleListValidation.forList(list -> list.stream().noneMatch(t -> t.equals(excludedValue)),
                withMessage(format("list mustn't contain %s", excludedValue)));
    }

    public static <T> Validation<List<T>> isNotEmpty() {
        return SimpleListValidation.forList(list -> list != null && !list.isEmpty(),
                withMessage("list mustn't be empty"));
    }

    public static <T> Validation<List<T>> isEmpty() {
        return SimpleListValidation.forList(list -> list != null && list.isEmpty(),
                withMessage("list must be empty"));
    }

    public static <T> Validation<List<T>> hasSize(int size) {
        return SimpleListValidation.forList(list -> list != null && list.size() == size,
                withMessage(format("size of list must be %s", size)));
    }

    public static <T> Validation<List<T>> hasMinSize(int size) {
        return SimpleListValidation.forList(list -> list != null && list.size() >= size,
                withMessage(format("size of list must be at least %s", size)));
    }

    public static <T> Validation<List<T>> hasMaxSize(int size) {
        return SimpleListValidation.forList(list -> list != null && list.size() <= size,
                withMessage(format("size of list mustn't be greater than %s", size)));
    }

    public static <T, E> Validation<List<T>> onProperty(Function<T, E> function, Validation<List<E>> validation) {
        return ListPropertValidationImpl.onProperty(function, validation);
    }

    public static <T> Validation<List<T>> forType(Class<T> listType, Validation<List<T>> validation) {
        return validation;
    }
}
