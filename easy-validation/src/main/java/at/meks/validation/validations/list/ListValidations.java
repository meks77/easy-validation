package at.meks.validation.validations.list;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withMessage;

/**
 * This class provides validations for lists. The methods always create new validation instances.
 */
@SuppressWarnings("WeakerAccess")
public class ListValidations {

    private static final ErrorMessageResolver MESSAGE_RESOLVER = new ErrorMessageResolver();

    private static final CoreListValidations VALIDATIONS = new CoreListValidations();

    private ListValidations() {

    }

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> containsOnly(T containedValue) {
        return containsOnly(() -> containedValue);
    }

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> containsOnly(Supplier<T> containedValue) {
        return VALIDATIONS.containsOnly(containedValue,
                () -> withMessage(MESSAGE_RESOLVER.getListContainsOnlyMessage(containedValue.get())));
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> contains(T containedValue) {
        return contains(() -> containedValue);
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> contains(Supplier<T> containedValue) {
        return VALIDATIONS.contains(containedValue,
                () -> withMessage(MESSAGE_RESOLVER.getListContainsMessage(containedValue.get())));
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> doesNotContain(T excludedValue) {
        return doesNotContain(() -> excludedValue);
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> doesNotContain(Supplier<T> excludedValue) {
        return VALIDATIONS.doesNotContain(excludedValue,
                () -> withMessage(MESSAGE_RESOLVER.getListDoesNotContainMessage(excludedValue.get())));
    }

    /**
     * validates that a list isn't empty. It mustn't be null and not empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isNotEmpty() {
        return VALIDATIONS.isNotEmpty(withMessage(MESSAGE_RESOLVER.getListIsNotEmptyMessage()));
    }

    /**
     * validates that a list is empty.
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isEmpty() {
        return VALIDATIONS.isEmpty(withMessage(MESSAGE_RESOLVER.getListIsEmptyMessage()));
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasSize(int size) {
        return hasSize(() -> size);
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasSize(Supplier<Integer> size) {
        return VALIDATIONS.hasSize(size, () -> withMessage(MESSAGE_RESOLVER.getListHasSizeMessage(size.get())));
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMinSize(int size) {
        return hasMinSize(() -> size);
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMinSize(Supplier<Integer> size) {
        return VALIDATIONS.hasMinSize(size, () -> withMessage(MESSAGE_RESOLVER.getListHasMinSizeMessage(size.get())));
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMaxSize(int size) {
        return hasMaxSize(() -> size);
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMaxSize(Supplier<Integer> size) {
        return VALIDATIONS.hasMaxSize(size, () -> withMessage(MESSAGE_RESOLVER.getListHasMaxSizeMessage(size.get())));
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
        return VALIDATIONS.onProperty(function, validation);
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
        return VALIDATIONS.forType(listType, validation);
    }
}
