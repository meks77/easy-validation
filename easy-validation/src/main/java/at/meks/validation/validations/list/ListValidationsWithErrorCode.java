package at.meks.validation.validations.list;

import at.meks.validation.ErrorMessageResolver;
import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescriptionBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static at.meks.validation.result.ErrorDescriptionBuilder.withCode;

/**
 * This class provides validations for lists with the support of error codes. The error code ist always part of the
 * validation result in the case of an validation violation. The methods always create new validation instances.
 */
@SuppressWarnings("WeakerAccess")
public class ListValidationsWithErrorCode {

    private ListValidationsWithErrorCode() {

    }

    private static ErrorMessageResolver messageResolver = new ErrorMessageResolver();

    private static CoreListValidations validations = new CoreListValidations();

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> containsOnly(T containedValue, String errorCode) {
        return containsOnly(() -> containedValue, errorCode);
    }

    /**
     * validates that a list contains only entries which match to the provided arg.
     * @param containedValue   the only value which is allowed in the list
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> containsOnly(Supplier<T> containedValue, String errorCode) {
        return validations.containsOnly(containedValue,
                () -> withCode(messageResolver.getListContainsOnlyMessage(containedValue.get()), errorCode));
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> contains(T containedValue, String errorCode) {
        return contains(() -> containedValue, errorCode);
    }

    /**
     * validates that a list contains at least one entry which matches to the provided arg.
     * @param containedValue    the value which must exist in the list
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> contains(Supplier<T> containedValue, String errorCode) {
        return validations.contains(containedValue,
                () -> withCode(messageResolver.getListContainsMessage(containedValue.get()), errorCode));
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> doesNotContain(T excludedValue, String errorCode) {
        return doesNotContain(() -> excludedValue, errorCode);
    }

    /**
     * validates that a list does not contain an entry which matches the provided arg.
     * @param excludedValue the value which mustn't exists in the list
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> doesNotContain(Supplier<T> excludedValue, String errorCode) {
        return validations.doesNotContain(excludedValue,
                () -> withCode(messageResolver.getListDoesNotContainMessage(excludedValue.get()), errorCode));
    }

    /**
     * validates that a list isn't empty. It mustn't be null and not empty.
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isNotEmpty(String errorCode) {
        return validations.isNotEmpty(withCode(messageResolver.getListIsNotEmptyMessage(), errorCode));
    }

    /**
     * validates that a list is empty.
     * @param errorCode error code used incase of an violation
     * @param <T>   the type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> isEmpty(String errorCode) {
        return validations.isEmpty(withCode(messageResolver.getListIsEmptyMessage(), errorCode));
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param errorCode error code used incase of an violation
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasSize(int size, String errorCode) {
        return hasSize(() -> size, errorCode);
    }

    /**
     * validates that a list has an expected size.
     * @param size  the expected size
     * @param errorCode error code used incase of an violation
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasSize(Supplier<Integer> size, String errorCode) {
        return validations.hasSize(size, () -> withCode(messageResolver.getListHasSizeMessage(size.get()), errorCode));
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param errorCode error code used incase of an violation
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMinSize(int size, String errorCode) {
        return hasMinSize(() -> size, errorCode);
    }

    /**
     * validates that a list has an expected minimum size.
     * @param size  the expected minimum size
     * @param errorCode error code used incase of an violation
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMinSize(Supplier<Integer> size, String errorCode) {
        return validations.hasMinSize(size, () -> withCode(messageResolver.getListHasMinSizeMessage(size.get()), errorCode));
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param errorCode error code used incase of an violation
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMaxSize(int size, String errorCode) {
        return hasMaxSize(() -> size, errorCode);
    }

    /**
     * validates that a list has an expected maximum size.
     * @param size  the expected maximum size
     * @param errorCode error code used incase of an violation
     * @param <T>   type of the list
     * @return  new instance of list validation
     */
    public static <T> Validation<List<T>> hasMaxSize(Supplier<Integer> size, String errorCode) {
        return validations.hasMaxSize(size, () -> withCode(messageResolver.getListHasMaxSizeMessage(size.get()), errorCode));
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
     * afterwards the entries should be validated by a property. If you would just start with {@link #isNotEmpty(String)} ()},
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
