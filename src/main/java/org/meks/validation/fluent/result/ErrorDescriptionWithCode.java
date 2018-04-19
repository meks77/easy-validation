package org.meks.validation.fluent.result;

class ErrorDescriptionWithCode implements ErrorDescription {

    private final String message;
    private final String errorCode;

    ErrorDescriptionWithCode(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return String.format("%s - %s", errorCode, message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
