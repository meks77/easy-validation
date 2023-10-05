package at.meks.validation.core;

public class MoreErrorsValidator {

    private ValidationErrorListener errorListener;

    public MoreErrorsValidator(ValidationErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public <T> MoreErrorsVerifier<T> verify(T validatedValue) {
        return new MoreErrorsVerifier<>(validatedValue, errorListener);
    }

}
