package org.meks.validation.fluent.result;

class ErrorDescriptionWithMessage implements ErrorDescription {


    private final String errorMessage;

    ErrorDescriptionWithMessage(String message) {
        this.errorMessage = message;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

}
