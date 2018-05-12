package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class provides validations for lists. The methods always create new validation instances.
 */
class CoreListValidations {

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> containsOnly(Supplier<T> containedValue, Supplier<ErrorDescription> errorDescription) {
        return SimpleListValidation.forList(list -> list.stream().allMatch(t -> t.equals(containedValue.get())),
                errorDescription);
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> contains(Supplier<T> containedValue, Supplier<ErrorDescription> errorDescription) {
        return SimpleListValidation.forList(list -> list.stream().anyMatch(t -> t.equals(containedValue.get())),
                errorDescription);
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> doesNotContain(Supplier<T> excludedValue, Supplier<ErrorDescription> errorDescription) {
        return SimpleListValidation.forList(list -> list.stream().noneMatch(t -> t.equals(excludedValue.get())),
                errorDescription);
    }

    /**
     * validates that a list isn't empty. It mustn't be null and not empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> isNotEmpty(ErrorDescription errorDescription) {
        return SimpleListValidation.forList(list -> list != null && !list.isEmpty(), errorDescription);
    }

    /**
     * validates that a list is empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> isEmpty(ErrorDescription errorDescription) {
        return SimpleListValidation.forList(list -> list != null && list.isEmpty(), errorDescription);
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> hasSize(Supplier<Integer> size, Supplier<ErrorDescription> errorDescription) {
        return SimpleListValidation.forList(list -> list != null && list.size() == size.get(), errorDescription);
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> hasMinSize(Supplier<Integer> size, Supplier<ErrorDescription> errorDescription) {
        return SimpleListValidation.forList(list -> list != null && list.size() >= size.get(), errorDescription);
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    <T> Validation<List<T>> hasMaxSize(Supplier<Integer> size, Supplier<ErrorDescription> errorDescription) {
        return SimpleListValidation.forList(list -> list != null && list.size() <= size.get(), errorDescription);
    }

    /**
     * validation for a property of the list entries.
     * @param function  mapper for the property of the list entries(like in streams)
     * @param validation    validation for the properties of the list. All validations for the property should be put here
     * @param <T>   type of the list
     * @param <E>   type of the validated property of the list entry
     * @return  new instance of a list validation
     */
    <T, E> Validation<List<T>> onProperty(Function<T, E> function, Validation<List<E>> validation) {
        return ListPropertyValidationImpl.onProperty(function, validation);
    }

    /**
     * sadly this method is needed if you want to start with general validations on lists, like the notEmpty, and
     * afterwards the entries should be validated by a property. If you would just start with {@link #isNotEmpty(ErrorDescription)} ()},
     * a Validation for {@link List} of type {@link Object} is returned, instead of the type of the list entries.
     * @param listType  the generic typ of the list, which will be validated
     * @param validation    the validation which should be invoked for the validated list.
     * @param <T>   the type of the validated list
     * @return  new instance of a list validation
     */
    <T> Validation<List<T>> forType(@SuppressWarnings("unused") Class<T> listType,
                                                  Validation<List<T>> validation) {
        // the arg listType is necessary that the generic type is returned for the list. otherwise it would be Object
        return validation;
    }
}
