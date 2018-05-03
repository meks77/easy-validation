package at.meks.validation;

import at.meks.validation.result.ErrorDescription;
import at.meks.validation.result.ValidationResult;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class SimpleValidation<T> implements Validation<T> {

    private final Predicate<T> predicate;
    private final Supplier<ErrorDescription> onErrorMessage;

    public static <K> SimpleValidation<K> from(Predicate<K> predicate, Supplier<ErrorDescription> onErrorMessage) {
        return new SimpleValidation<>(predicate, onErrorMessage);
    }

    private SimpleValidation(Predicate<T> predicate, Supplier<ErrorDescription> onErrorMessage) {
        this.predicate = predicate;
        this.onErrorMessage = onErrorMessage;
    }

    @Override
    public ValidationResult test(T param) {
        return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail(onErrorMessage.get());
    }

}
