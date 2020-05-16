package at.meks.validation.core;

@FunctionalInterface
public interface ValidationErrorListener {

    void onValidationError(String key);
}
