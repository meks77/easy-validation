package org.meks.validation.fluent.result;

public class ValidationException extends Exception {

    private final String valueDescription;
    private final ErrorDescription errorDescription;

    ValidationException(String valueDescription, ErrorDescription errorDescription) {
        this.valueDescription = valueDescription;
        this.errorDescription = errorDescription;
    }

    @Override
    public String getMessage() {
        return String.format("%s: %s", valueDescription, errorDescription.getErrorMessage());
    }
}
