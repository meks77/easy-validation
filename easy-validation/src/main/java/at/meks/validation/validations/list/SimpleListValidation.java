package at.meks.validation.validations.list;

import at.meks.validation.Validation;
import at.meks.validation.result.ErrorDescription;
import at.meks.validation.result.ValidationResult;

import java.util.List;
import java.util.function.Predicate;

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
    public ValidationResult test(List<T> list) {
        return predicate.test(list) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage);
    }

}
