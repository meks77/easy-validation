package org.meks.validation.fluent.result;

public class ValidationException extends Exception {

    private String valueDescription;
    private final ErrorDescription errorDescription;

    ValidationException(String valueDescription, ErrorDescription errorDescription) {
        this.valueDescription = valueDescription;
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        if (errorDescription != null) {
            return String.format("%s: %s", valueDescription, errorDescription.getErrorMessage());
        }
        return valueDescription;
    }
}
