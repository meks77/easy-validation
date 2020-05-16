package at.meks.validation.core;

public class MoreErrorsVerifier<T> {

    private final T validatedValue;
    private final ValidationErrorListener errorListener;
    private String errorKey;

    MoreErrorsVerifier(T validatedValue, ValidationErrorListener errorListener) {
        this.validatedValue = validatedValue;
        this.errorListener = errorListener;
    }

    public MoreErrorsVerifier<T> usingKey(String errorKey) {
        this.errorKey = errorKey;
        return this;
    }

    public MoreErrorsVerifier<T> matches(Matcher<T> matcher) {
        if (!matcher.verify(validatedValue)) {
            errorListener.onValidationError(errorKey);
        }
        return this;
    }

    public MoreErrorsVerifier<T> and() {
        return this;
    }

}