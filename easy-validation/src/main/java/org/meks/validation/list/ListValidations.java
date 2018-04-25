package org.meks.validation.list;

import org.meks.validation.Validation;
import org.meks.validation.result.ErrorDescriptionBuilder;

import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * This class provides validations for lists. The methods always create new validation instances.
 */
public class ListValidations {

    private ListValidations() {

    }

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> containsOnly(T containedValue) {
        return SimpleListValidation.forList(list -> list.stream().allMatch(t -> t.equals(containedValue)),
                ErrorDescriptionBuilder.withMessage(format("list must contain only %s", containedValue)));
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> contains(T containedValue) {
        return SimpleListValidation.forList(list -> list.stream().anyMatch(t -> t.equals(containedValue)),
                ErrorDescriptionBuilder.withMessage(format("list must contain %s", containedValue)));
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> doesNotContain(T excludedValue) {
        return SimpleListValidation.forList(list -> list.stream().noneMatch(t -> t.equals(excludedValue)),
                ErrorDescriptionBuilder.withMessage(format("list mustn't contain %s", excludedValue)));
    }

    /**
     * validates that a list isn't empty. It mustn't be null and not empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isNotEmpty() {
        return SimpleListValidation.forList(list -> list != null && !list.isEmpty(),
                ErrorDescriptionBuilder.withMessage("list mustn't be empty"));
    }

    /**
     * validates that a list is empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isEmpty() {
        return SimpleListValidation.forList(list -> list != null && list.isEmpty(),
                ErrorDescriptionBuilder.withMessage("list must be empty"));
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasSize(int size) {
        return SimpleListValidation.forList(list -> list != null && list.size() == size,
                ErrorDescriptionBuilder.withMessage(format("size of list must be %s", size)));
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMinSize(int size) {
        return SimpleListValidation.forList(list -> list != null && list.size() >= size,
                ErrorDescriptionBuilder.withMessage(format("size of list must be at least %s", size)));
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMaxSize(int size) {
        return SimpleListValidation.forList(list -> list != null && list.size() <= size,
                ErrorDescriptionBuilder.withMessage(format("size of list mustn't be greater than %s", size)));
    }

    /**
     * validation for a property of the list entries.
     * @param function  mapper for the property of the list entries(like in streams)
     * @param validation    validation for the properties of the list. All validations for the property should be put here
     * @param <T>   type of the list
     * @param <E>   type of the validated property of the list entry
     * @return  new instance of a list validation
     */
    public static <T, E> Validation<List<T>> onProperty(Function<T, E> function, Validation<List<E>> validation) {
        return ListPropertyValidationImpl.onProperty(function, validation);
    }

    /**
     * sadly this method is needed if you want to start with general validations on lists, like the notEmpty, and
     * afterwards the entries should be validated by a property. If you would just start with {@link #isNotEmpty()},
     * a Validation for {@link List} of type {@link Object} is returned, instead of the type of the list entries.
     * @param listType  the generic typ of the list, which will be validated
     * @param validation    the validation which should be invoked for the validated list.
     * @param <T>   the type of the validated list
     * @return  new instance of a list validation
     */
    public static <T> Validation<List<T>> forType(@SuppressWarnings("unused") Class<T> listType,
                                                  Validation<List<T>> validation) {
        // the arg listType is necessary that the generic type is returned for the list. otherwise it would be Object
        return validation;
    }
}
