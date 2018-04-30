package org.meks.validation.validations.list;

import org.meks.validation.ErrorMessageResolver;
import org.meks.validation.Validation;

import java.util.List;
import java.util.function.Function;

import static org.meks.validation.result.ErrorDescriptionBuilder.withMessage;

/**
 * This class provides validations for lists. The methods always create new validation instances.
 */
@SuppressWarnings("WeakerAccess")
public class ListValidations {

    private ListValidations() {

    }

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();

    private static CoreListValidations validations = new CoreListValidations();

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> containsOnly(T containedValue) {
        return validations.containsOnly(containedValue,
                withMessage(messageResolver.getListContainsOnlyMessage(containedValue)));
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> contains(T containedValue) {
        return validations.contains(containedValue,
                withMessage(messageResolver.getListContainsMessage(containedValue)));
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> doesNotContain(T excludedValue) {
        return validations.doesNotContain(excludedValue,
                withMessage(messageResolver.getListDoesNotContainMessage(excludedValue)));
    }

    /**
     * validates that a list isn't empty. It mustn't be null and not empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isNotEmpty() {
        return validations.isNotEmpty(withMessage(messageResolver.getListIsNotEmptyMessage()));
    }

    /**
     * validates that a list is empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isEmpty() {
        return validations.isEmpty(withMessage(messageResolver.getListIsEmptyMessage()));
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasSize(int size) {
        return validations.hasSize(size, withMessage(messageResolver.getListHasSizeMessage(size)));
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMinSize(int size) {
        return validations.hasMinSize(size, withMessage(messageResolver.getListHasMinSizeMessage(size)));
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMaxSize(int size) {
        return validations.hasMaxSize(size, withMessage(messageResolver.getListHasMaxSizeMessage(size)));
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
        return validations.onProperty(function, validation);
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
    public static <T> Validation<List<T>> forType(Class<T> listType,
                                                  Validation<List<T>> validation) {
        return validations.forType(listType, validation);
    }
}
